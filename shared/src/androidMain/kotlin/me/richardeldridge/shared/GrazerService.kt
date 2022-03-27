package me.richardeldridge.shared

import me.richardeldridge.shared.pojos.auth.Authenticate
import me.richardeldridge.shared.pojos.users.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Example of retrofit taken from https://www.raywenderlich.com/6994782-android-networking-with-kotlin-tutorial-getting-started
 */
interface GrazerService {

    @POST("/auth/login")
    fun doAuthentication(@Body body: String): Call<Authenticate>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/users")
    fun retrieveUserData(@Header("Authorization") auth: String ): Call<User>

}