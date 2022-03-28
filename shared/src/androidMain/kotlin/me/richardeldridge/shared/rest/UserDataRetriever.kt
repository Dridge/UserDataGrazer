package me.richardeldridge.shared.rest

import me.richardeldridge.shared.GrazerService
import me.richardeldridge.shared.pojos.users.Users
import retrofit2.Callback


class UserDataRetriever() {
    private val service: GrazerService = GrazerService.retrofit.create(GrazerService::class.java)

    fun getUsers(callback: Callback<Users>, bearerToken: String) {
        retrieveUserData(callback, bearerToken)
    }

    private fun retrieveUserData(callback: Callback<Users>, token: String) {
        println(token)
        val call = service.retrieveUserData("Bearer $token")
        call.enqueue(callback)
    }
}