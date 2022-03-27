package me.richardeldridge.shared.rest

import android.util.Log
import me.richardeldridge.shared.pojos.users.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.test.Test

class UserDataRequestTest {

    @Test
    fun testGetUsersDoesNotThrowAnError() {
        val underTest = UserDataRetriever()
        val callback = object : Callback<User> {
            override fun onFailure(call: Call<User>, t:Throwable) {
                Log.e("MainActivity", "Problem calling Github API {${t.message}}")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.isSuccessful.let {
                    //do nothing
                }
            }
        }
        underTest.getUsers(callback, "t5", "blah");
    }
}