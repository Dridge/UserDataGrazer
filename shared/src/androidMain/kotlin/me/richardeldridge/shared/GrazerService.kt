package me.richardeldridge.shared

import me.richardeldridge.shared.pojos.auth.Authenticate
import me.richardeldridge.shared.pojos.users.UserData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Example of retrofit taken from https://www.raywenderlich.com/6994782-android-networking-with-kotlin-tutorial-getting-started
 */
interface GrazerService {
    companion object {
        private const val BASE_URL = "https://grazer-test.herokuapp.com/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @POST("v1/auth/login")
    fun doAuthentication(@Body body: RequestBody): Call<Authenticate>

    @Headers("Content-Type: application/json;charset=UTF-8",
        "Accept: application/json")
    @GET("v1/users")
    fun retrieveUserData(@Header("Authorization") auth: String ): Call<UserData>

}