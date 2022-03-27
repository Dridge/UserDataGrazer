package me.richardeldridge.shared.rest

import android.util.Log
import me.richardeldridge.shared.GrazerService
import me.richardeldridge.shared.GrazerService.Companion.retrofit
import me.richardeldridge.shared.pojos.auth.Authenticate
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Sets the token once authenticated
 */
class Authenticator {
    private val service: GrazerService = retrofit.create(GrazerService::class.java)
    var token = ""
    private val callback = object : Callback<Authenticate> {
        override fun onFailure(call: Call<Authenticate>, t:Throwable) {
            println("ERROR - failed whilst authenticating user")
            Log.e("Authenticator", "Problem calling Grazer API {${t.message}}")
            throw t
        }
        override fun onResponse(call: Call<Authenticate>, response: Response<Authenticate>) {
            token = response.body()?.auth?.data?.token.toString()
        }
    }

    fun login(username: String, password: String) {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        service.doAuthentication(getAuthBody(username, password).toRequestBody(jsonMediaType)).enqueue(callback)
    }

    fun isAuthenticated(): Boolean {
       return token.isNotBlank()
    }

    private fun getAuthBody(username: String, password: String): String {
        return "{ \"email\":\"$username\",\"password\":\"$password\"}"
    }

}