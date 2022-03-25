package me.richardeldridge.shared.rest

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.security.keystore.UserNotAuthenticatedException
import com.fasterxml.jackson.databind.ObjectMapper
import me.richardeldridge.shared.pojos.auth.Authenticate
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class UserDataRequest() {
    private val grazerUrl = "https://grazer-test.herokuapp.com/v1/"
    private val authEndpoint = "auth/login"
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    fun getUsers(username: String, password: String): String {
        val token = authenticateUser(username, password) ?: throw UserNotAuthenticatedException()
        return doAuthenticatedGetCall(token, "users")
    }

    private fun authenticateUser(username: String, password: String): String? {
        //username is actually email at the moment
        val body = "{ \"email\":\"$username@gmail.com\",\"password\":\"$password\"}"
        val response = doPostCall(grazerUrl.plus(authEndpoint), body)
        return getTokenFromResponse(response)
    }

    private fun doAuthenticatedGetCall(token: String, endPoint: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(grazerUrl.plus(endPoint))
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .get()
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code: ${response.code}")
            return response.body!!.source().readUtf8()
        }
    }

    private fun doPostCall(url: String, jsonString: String): String {
        val client = OkHttpClient()
        //build json post request
        val body: RequestBody = jsonString.toRequestBody(jsonMediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        println(url)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code: ${response.code}")
            return response.body!!.source().readUtf8()
        }
    }

    private fun getTokenFromResponse(response: String): String {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(response, Authenticate::class.java).auth!!.data!!.token.toString()
    }

}