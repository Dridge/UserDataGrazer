package me.richardeldridge.androidApp.userData

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import me.richardeldridge.androidApp.R
import me.richardeldridge.shared.pojos.users.User
import me.richardeldridge.shared.rest.UserDataRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDataActivity : AppCompatActivity() {
    private val userDataRetriever = UserDataRetriever()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        val intent = this.intent
        val username = intent.getCharSequenceExtra("username").toString()
        val token = intent.getCharSequenceExtra("token").toString()
        val getUserDataBtn = findViewById<Button>(R.id.getUsers)
        val userDataListView = findViewById<ListView>(R.id.userDataListView)

        val callback = object : Callback<User> {
            override fun onFailure(call: Call<User>, t:Throwable) {
                println("ERROR - failure whilst getting users data")
                Log.e("MainActivity", "Problem calling Github API {${t.message}}")
                Toast.makeText(this@UserDataActivity, "Error retrieving user data!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.isSuccessful.let {
                    val resultList = ArrayList<User>()
                    resultList.add(Gson().fromJson(response.body().toString(), User::class.java))
                    userDataListView.adapter = UserDataAdapter(applicationContext, resultList)
                }
            }
        }
        userDataRetriever.getUsers(callback, token)
        //TODO reintroduce button click to refresh?
    }
}