package me.richardeldridge.androidApp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import me.richardeldridge.shared.rest.UserDataRetriever


class UserDataActivity : AppCompatActivity() {
    //TODO lateinit userDataListView?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdata)

        val intent = this.intent
        val username = intent.getCharSequenceExtra("username").toString()
        val password = intent.getCharSequenceExtra("password").toString()

        val getUserDataBtn = findViewById<Button>(R.id.getUsers)
        var userDataListView = findViewById<ListView>(R.id.userData)
        val listUsers = arrayOfNulls<String>(1)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers)
        userDataListView.adapter = adapter
        getUserDataBtn.setOnClickListener {
            Thread(Runnable {
                this@UserDataActivity.runOnUiThread(java.lang.Runnable {
                    (userDataListView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                })
            }).start()
//            userDataListView.text = getUserData(username, password)
        }
    }

    private fun getUserData(username: String, password: String): String {
        val userDataRequest = UserDataRetriever()
        return userDataRequest.getUsers(username, password)
    }
}