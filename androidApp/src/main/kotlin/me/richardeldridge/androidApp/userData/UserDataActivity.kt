package me.richardeldridge.androidApp.userData

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import me.richardeldridge.androidApp.R
import me.richardeldridge.shared.pojos.users.Users
import me.richardeldridge.shared.rest.UserDataRetriever
import me.richardeldridge.shared.rest.authentication.Authenticator
import me.richardeldridge.shared.rest.authentication.IAuthenticationObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDataActivity :  IAuthenticationObserver, AppCompatActivity() {
    private val userDataRetriever = UserDataRetriever()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Add this as an observer to the authentication
        val auth = Authenticator.getInstance()
        auth?.add(this)
        setContentView(R.layout.activity_userdata)

        val getUserDataBtn = findViewById<Button>(R.id.getUsers)
        val userDataListView = findViewById<ListView>(R.id.userDataListView)

        val callback = object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t:Throwable) {
                println("ERROR - failure whilst getting users data")
                Log.e("MainActivity", "Problem calling Github API {${t.message}}")
                Toast.makeText(this@UserDataActivity, "Error retrieving user data!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                response.isSuccessful.let {
                    val resultList = ArrayList<Users>()
                    println(response.body().toString())
                    resultList.add(Gson().fromJson(response.body().toString(), Users::class.java))
                    userDataListView.adapter = UserDataAdapter(applicationContext, resultList)
                }
            }
        }
        getUserDataBtn.setOnClickListener {
            if(auth?.isAuthenticated() ==true) {
                Toast.makeText(this@UserDataActivity, "Lets get that data!", Toast.LENGTH_LONG).show()
                userDataRetriever.getUsers(callback, Authenticator.getToken())
            } else {
                Toast.makeText(this@UserDataActivity, "Waiting log in...", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Once authenticated update views or buttons that are permitted
     * And if the token is removed/invalidated disable them
     */
    override fun update() {
        //do nothing
    }
}