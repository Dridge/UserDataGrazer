package me.richardeldridge.androidApp.userData

import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.richardeldridge.androidApp.R.id.getUsers
import me.richardeldridge.androidApp.R.id.userDataListView
import me.richardeldridge.androidApp.R.layout.activity_userdata
import me.richardeldridge.shared.rest.IUserDataObserver
import me.richardeldridge.shared.rest.UserDataRetriever
import me.richardeldridge.shared.rest.authentication.Authenticator

class UserDataActivity :  IUserDataObserver, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_userdata)
        UserDataRetriever.add(this)
        val getUserDataBtn = findViewById<Button>(getUsers)
        getUserDataBtn.setOnClickListener {
            if(Authenticator.getInstance().isAuthenticated()) {
                Toast.makeText(this@UserDataActivity, "Lets get that data!", Toast.LENGTH_LONG).show()
                UserDataRetriever.getInstance().populateUserData(Authenticator.getToken())
                //Hide the button to prevent a second click
                getUserDataBtn.visibility = INVISIBLE
            } else {
                Toast.makeText(this@UserDataActivity, "Wait, still logging in...", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Once userdata is recieved updat the listview adapter, just once
     *
     */
    override fun update() {
        val userDataListView = findViewById<ListView>(userDataListView)
        userDataListView.adapter = UserDataAdapter(applicationContext,
            UserDataRetriever.getInstance().getUserData())
        Toast.makeText(this@UserDataActivity, "Data retrieved!", Toast.LENGTH_LONG).show()
    }
}