package me.richardeldridge.androidapp.userdata

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.richardeldridge.androidapp.R
import me.richardeldridge.androidapp.R.id.getUsers
import me.richardeldridge.androidapp.R.id.userDataListView
import me.richardeldridge.androidapp.R.layout.activity_userdata
import me.richardeldridge.shared.rest.IUserDataObserver
import me.richardeldridge.shared.rest.UserDataRetriever
import me.richardeldridge.shared.rest.authentication.Authenticator
import me.richardeldridge.shared.rest.authentication.IAuthenticationObserver

class UserDataActivity : IAuthenticationObserver, IUserDataObserver, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_userdata)
        UserDataRetriever.add(this)
        Authenticator.add(this)
        val getUserDataBtn = findViewById<Button>(getUsers)
        getUserDataBtn.setOnClickListener {
            setOnClickFunctionality()
        }
    }

    private fun setOnClickFunctionality() {
        //differ more complex logic to the update, to cater for authentication withdrawal while waiting for response
        Toast.makeText(this@UserDataActivity, "Lets get that data!", Toast.LENGTH_SHORT).show()
        // prevent multiple clicks
        disableButton()
        // request data
        UserDataRetriever.getInstance().populateUserData()
    }

    private fun enableButton() {
        val getDataButton = findViewById<Button>(getUsers)
        getDataButton.setBackgroundResource(R.drawable.border_shaded_selector)
        getDataButton.isClickable = true
    }

    private fun disableButton() {
        val getDataButton = findViewById<Button>(getUsers)
        getDataButton.setBackgroundResource(R.drawable.border_shaded_disabled_selector)
        getDataButton.isClickable = false
    }

    /**
     * Once userdata is received update the listview adapter, just once
     *  but only if it's still authenticated i.e. token is still available in authenticator
     * Because we observe both authentication and user data, we can choose whether to update based on
     * more than one update scenario. We can check our observables and see what state they're in.
     */
    override fun update() {
        if (Authenticator.isAuthenticated()) {
            if (UserDataRetriever.isUserDataAvailable()) {
                findViewById<ListView>(userDataListView).apply {
                    adapter = UserDataAdapter(
                        applicationContext,
                        UserDataRetriever.getInstance().getUserData()
                    )
                }
                Toast.makeText(this@UserDataActivity, "Data retrieved!", Toast.LENGTH_SHORT).show()
                // make sure button is disabled
                disableButton()
            } else {
                enableButton()
            }
        } else {
            Toast.makeText(this@UserDataActivity, "User not logged in, log in again please.", Toast.LENGTH_SHORT).show()
            disableButton()
        }
    }
}
