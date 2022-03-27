package me.richardeldridge.shared.rest

import me.richardeldridge.shared.GrazerService
import me.richardeldridge.shared.pojos.users.User
import retrofit2.Callback


class UserDataRetriever() {
    private val service: GrazerService = GrazerService.retrofit.create(GrazerService::class.java)

    fun getUsers(callback: Callback<User>, bearerToken: String) {
        retrieveUserData(callback, bearerToken)
    }

    private fun retrieveUserData(callback: Callback<User>, token: String) {
        val call = service.retrieveUserData("application/json", "Bearer $token")
        call.enqueue(callback)
    }
}