package me.richardeldridge.shared.rest

import android.util.Log
import me.richardeldridge.shared.pojos.users.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.test.Test

class UserDataRequestTest {

    @Test
    fun testGetUsersDoesNotThrowAnError() {
        //TODO test auth?
        val underTest = UserDataRetriever()
        val callback = object : Callback<UserData> {
            override fun onFailure(call: Call<UserData>, t:Throwable) {
                Log.e("MainActivity", "Problem calling Github API {${t.message}}")
            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                response.isSuccessful.let {
                    //do nothing
                }
            }
        }
        //underTest.getUsers(callback, "t5");
    }
}