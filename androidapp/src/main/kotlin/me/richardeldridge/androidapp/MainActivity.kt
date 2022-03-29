package me.richardeldridge.androidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import me.richardeldridge.androidapp.userdata.UserDataActivity
import me.richardeldridge.shared.rest.authentication.Authenticator
import me.richardeldridge.shared.rest.authentication.IAuthenticationObserver

class MainActivity : IAuthenticationObserver, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        setupResetButton(username, password)
        setupSubmitButton(username, password)
    }

    private fun setupSubmitButton(username: EditText, password: EditText) {
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val usernameString = username.text.toString()
            val passwordString = password.text.toString()
            if (isUsernameOrPasswordBlank(usernameString, passwordString)) {
                Toast.makeText(this@MainActivity, "Please enter both username and password.", Toast.LENGTH_SHORT).show()
            } else {
                runBlocking { Authenticator.getInstance().login(usernameString, passwordString) }
                val intent = Intent(this, UserDataActivity::class.java)
                startActivity(intent)
                resetLogin(username, password)
            }
        }
    }

    private fun setupResetButton(username: EditText, password: EditText) {
        val reset = findViewById<Button>(R.id.reset)
        Authenticator.getInstance().add(this)
        reset.setOnClickListener {
            resetLogin(username, password)
            Toast.makeText(this@MainActivity, "Reset login details.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetLogin(username: EditText, password: EditText) {
        username.setText("")
        password.setText("")
    }

    /**
     * Don't allow empty inputs
     */
    private fun isUsernameOrPasswordBlank(username: String, password: String): Boolean =
             username.isBlank() || password.isBlank()

    override fun update() {
        if (Authenticator.getInstance().isAuthenticated()) {
            Toast.makeText(this@MainActivity, "You are signed in, welcome!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@MainActivity, "Login denied, are your details correct?", Toast.LENGTH_LONG).show()
        }
    }
}
