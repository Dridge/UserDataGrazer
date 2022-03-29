package me.richardeldridge.shared.rest

import android.util.Log
import me.richardeldridge.shared.GrazerService
import me.richardeldridge.shared.pojos.users.UserData
import me.richardeldridge.shared.pojos.users.Users
import me.richardeldridge.shared.rest.authentication.Authenticator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserDataRetriever : IUserDataObservable {
    private var instance = UserDataRetriever
    private val service: GrazerService = GrazerService.retrofit.create(GrazerService::class.java)
    override val observers: ArrayList<IUserDataObserver> = ArrayList()

    private var observableUsers = arrayListOf<Users>()
        set(value) {
            field = value
            UserDataRetriever.sendUpdateEvent()
        }

    fun getInstance(): UserDataRetriever = instance

    /**
     * @param bearerToken
     */
    fun populateUserData() {
        val callback = object : Callback<UserData> {
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                println("ERROR - failure whilst getting users data")
                Log.e("MainActivity", "Problem calling Github API {${t.message}}")
            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                response.isSuccessful.let {
                    observableUsers = response.body()!!.data?.users!!
                }
            }
        }
        val token = Authenticator.getToken()
        val call = service.retrieveUserData("Bearer $token")
        call.enqueue(callback)
    }

    fun getUserData(): ArrayList<Users> = observableUsers

    fun isUserDataAvailable(): Boolean {
        return observableUsers.isNotEmpty()
    }
}
