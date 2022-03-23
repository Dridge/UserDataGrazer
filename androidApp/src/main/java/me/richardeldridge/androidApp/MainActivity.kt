package me.richardeldridge.androidApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val reset = findViewById<Button>(R.id.reset)
        val submit = findViewById<Button>(R.id.submit)

        reset.setOnClickListener {
            resetLogin(username, password)
        }
        submit.setOnClickListener {
            val usernameText = username.text
            val passwordText = password.text

            //accept most username and password combinations for now
            if (isValidPassword(passwordText.toString())) {
                Toast.makeText(this@MainActivity, "Welcome $usernameText!", Toast.LENGTH_LONG).show()
                // go to the next activity and reset the login
                val intent = Intent(this, UserDataActivity::class.java)
                intent.putExtra("username", usernameText)
                resetLogin(username, password)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Password not permitted, alpha numeric only.", Toast.LENGTH_LONG).show()
                resetLogin(username, password)
            }
        }
    }

    private fun resetLogin(username: EditText, password: EditText) {
        username.setText("")
        password.setText("")
    }

    /**
     * Only allow alphanumerical characters for passwords at the moment
     * Force at least one number and one character
     */
    private fun isValidPassword(password: String): Boolean {
        val isNumberInPassword = password.contains(Regex("[0-9]"))
        println("isNumberInPassword: $isNumberInPassword")
        val isCharactersInPassword = password.contains(Regex("[a-zA-Z]"))
        println("isCharactersInPassword: $isCharactersInPassword")
        return isNumberInPassword && isCharactersInPassword
    }
}
