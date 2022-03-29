package me.richardeldridge.shared.rest.authentication

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
 * Authenticator for the app
 * Sets the token once authenticated
 */
object Authenticator : IAuthenticationObservable {
    private var instance = Authenticator
    private val service: GrazerService = retrofit.create(GrazerService::class.java)
    override val observers: ArrayList<IAuthenticationObserver> = ArrayList()

    // Override the setter for the observableToken property
    private var observableToken = ""
        set(value) {
            field = value
            sendUpdateEvent()
        }

    private val callback = object : Callback<Authenticate> {
        override fun onFailure(call: Call<Authenticate>, t: Throwable) {
            println("ERROR - failed whilst authenticating user")
            Log.e("Authenticator", "Problem calling Grazer API {${t.message}}")
            throw t
        }
        override fun onResponse(call: Call<Authenticate>, response: Response<Authenticate>) {
            observableToken = response.body()?.auth?.data
                    ?.token.toString()
        }
    }

    fun getInstance(): Authenticator = instance

    /**
     * @param username
     * @param password
     */
    fun login(username: String, password: String) {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        service.doAuthentication(getAuthBody(username, password).toRequestBody(jsonMediaType)).enqueue(callback)
    }

    /**
     * @return
     */
    fun isAuthenticated(): Boolean = observableToken.isNotBlank()

    fun getToken(): String = observableToken

    private fun getAuthBody(username: String, password: String): String =
        "{ \"email\":\"$username\",\"password\":\"$password\"}"
}
