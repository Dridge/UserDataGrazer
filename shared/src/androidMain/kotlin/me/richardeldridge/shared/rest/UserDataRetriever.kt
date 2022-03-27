package me.richardeldridge.shared.rest

import com.fasterxml.jackson.databind.ObjectMapper
import me.richardeldridge.shared.GrazerService
import me.richardeldridge.shared.pojos.auth.Authenticate
import me.richardeldridge.shared.pojos.users.User
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserDataRetriever() {
    private val service: GrazerService
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()
    companion object {
        const val BASE_URL = "https://grazer-test.herokuapp.com/v1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(GrazerService::class.java)
    }

    fun getUsers(username: String, password: String): String {
        val authenticateCall = authenticateUser(username, password)
        return doAuthenticatedGetCall(authenticateCall)
    }

    private fun authenticateUser(username: String, password: String): Call<Authenticate> {
        //username is actually email at the moment
        val body = "{ \"email\":\"$username@gmail.com\",\"password\":\"$password\"}" //todo remove @gmail
        return doPostCall(BASE_URL.plus("/auth/login"), body)
    }

    /**
     * Using fancy retrofit, to allow for asynchronous calls
     */
    private fun doAuthenticatedGetCall(authenticateCall: Call<Authenticate>): String {

        val call: Call<User> = service.retrieveUserData("Bearer TODO")
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                val userResponse: User? = response.body()
                println("User response received:")
                println(userResponse)
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                println("ERROR - failure whilst getting users data")
            }
        })
//        val request = Request.Builder()
//            .url(BASE_URL.plus(endPoint))
//            .addHeader("Accept", "application/json")
//            .addHeader("Authorization", "Bearer $token")
//            .get()
//            .build()
//        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code: ${response.code}")
//            return response.body!!.source().readUtf8()
            return "user data in logs..."
        }

    private fun doPostCall(url: String, jsonString: String): Call<Authenticate> {
//        val client = OkHttpClient()
//        //build json post request
//        val body: RequestBody = jsonString.toRequestBody(jsonMediaType)
//        val request = Request.Builder()
//            .url(url)
//            .post(body)
//            .build()
//        println(url)
//        // asynchronously?
//        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code: ${response.code}")
//            return response.body!!.source().readUtf8()
//        }
        val call: Call<Authenticate> = service.doAuthentication(jsonString)
        call.enqueue(object : Callback<Authenticate> {
            override fun onResponse(call: Call<Authenticate>, response: retrofit2.Response<Authenticate>) {
                val authResponse: Authenticate? = response.body()
                println("Authenticate response received:")
                println(authResponse)
            }
            override fun onFailure(call: Call<Authenticate>, t: Throwable) {
                println("ERROR - failure whilst getting users data")
            }
        })
        return call
    }

    private fun getTokenFromResponse(response: String): String {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(response, Authenticate::class.java).auth!!.data!!.token.toString()
    }

}