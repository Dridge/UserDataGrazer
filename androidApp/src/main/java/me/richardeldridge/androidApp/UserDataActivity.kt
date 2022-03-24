package me.richardeldridge.androidApp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.richardeldridge.shared.rest.RestCallFactory

class UserDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        val intent = this.intent
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        if (username == null || password == null) {
            // Checks on login activity _should_ never allow nulls
            Toast.makeText(this@UserDataActivity, "Error, unable to display data!", Toast.LENGTH_LONG)
        }
        val restCallBuilder = RestCallFactory()
        val restCall = restCallBuilder.getAuthenticatedRestCall(username, password, "users")

        //display data on screen
    }

}