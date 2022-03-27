package me.richardeldridge.androidApp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import me.richardeldridge.androidApp.userData.UserDataActivity
import me.richardeldridge.shared.rest.Authenticator

class MainActivity : AppCompatActivity() {
    private val apiResponseTimeLimitMs = 10000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val reset = findViewById<Button>(R.id.reset)
        val submit = findViewById<Button>(R.id.submit)
        val spinner = findViewById<ProgressBar>(R.id.progressBar);
        reset.setOnClickListener {
            resetLogin(username, password)
        }
        submit.setOnClickListener {
            spinner.visibility = View.VISIBLE
            val usernameString = username.text.toString()
            val passwordString = password.text.toString()
            val auth = Authenticator()
            runBlocking { auth.login(usernameString, passwordString) }
            val intent = Intent(this, UserDataActivity::class.java)
            intent.putExtra("username", usernameString)
            intent.putExtra("token", auth.token)
            startActivity(intent)
            resetLogin(username, password)
            spinner.visibility = View.GONE
        }
    }

    private fun isLoggedIn(usernameString: String, passwordString: String, auth: Authenticator): Boolean {
        if (isUsernameOrPasswordBlank(usernameString, passwordString)) {
            Toast.makeText(this@MainActivity, "Password not permitted, alpha numeric only.", Toast.LENGTH_LONG).show()
        } else if (auth.isAuthenticated()) {
            Toast.makeText(this@MainActivity, "Welcome $usernameString!", Toast.LENGTH_LONG).show()
            return true
        } else {
            Toast.makeText(this@MainActivity, "Login denied, are your details correct?", Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun resetLogin(username: EditText, password: EditText) {
        username.setText("")
        password.setText("")
    }

    /**
     * Don't allow empty inputs
     */
    private fun isUsernameOrPasswordBlank(username: String, password: String): Boolean {
        return username.isBlank() && password.isBlank()
    }
}
