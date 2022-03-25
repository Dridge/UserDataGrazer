package me.richardeldridge.androidApp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.richardeldridge.shared.rest.UserDataRequest

class UserDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        val intent = this.intent
        val getUserDataBtn = findViewById<Button>(R.id.showUsers)
        val userDataText = findViewById<TextView>(R.id.userData)
        val username = intent.getCharSequenceExtra("username").toString()
        val password = intent.getCharSequenceExtra("password").toString()

        getUserDataBtn.setOnClickListener {
            userDataText.text = getUserData(username, password)
        }
    }

    private fun getUserData(username: String, password: String): String {
        val userDataRequest = UserDataRequest()
        return userDataRequest.getUsers(username, password)
    }

}