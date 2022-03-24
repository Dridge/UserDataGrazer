package me.richardeldridge.shared.rest

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.io.IOException


class RestCallFactory() {
    private val grazerUrl = "https://grazer-test.herokuapp.com/v1/"
    private val authEndpoint = "auth/login"
    private val JSON = "application/json; charset=utf-8".toMediaType()

    private fun getAuthenticatedRestCall(username: String?, password: String?, endpoint: String?) {
        //username is actually email at the moment
        val body = ""
        val token = getToken(doPostCall(grazerUrl.plus("/").plus(authEndpoint), body))
    }

    private fun getToken(response: ResponseBody?): String? {
        return ""
    }

    private fun doPostCall(url: String, json: String): ResponseBody? {
        val client = OkHttpClient()
        //build json post request

        val body: RequestBody = json.toRequestBody(JSON)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        //post request to authenticate
        //token returned
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code: ${response.code}")
            return response.body
        }
    }

    fun getUsersRestCall(username: String?, password: String?) {
//        return getAuthenticatedRestCall(username, password, "users")
    }
}