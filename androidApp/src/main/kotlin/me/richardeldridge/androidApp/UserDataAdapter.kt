package me.richardeldridge.androidApp

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.richardeldridge.shared.pojos.users.User

/**
 * Based on the tutorial explained here: https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin
 */
class UserDataAdapter(private val context: Context, private val dataSource: ArrayList<UserData>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.activity_userdata, parent, false)
        // Get title element
        val name = rowView.findViewById(R.id.name) as TextView
        // Get subtitle element
        val dateOfBirth = rowView.findViewById(R.id.dateOfBirth) as TextView
        // Get thumbnail element
        val userPhoto = rowView.findViewById(R.id.userPhoto) as ImageView

        val user = getItem(position) as User
        name.text = user.name
        dateOfBirth.text = user.dateOfBirth.toString()

        Picasso.with(context).load(user.profileImage).placeholder(R.mipmap.ic_launcher).into(userPhoto)
        return rowView
    }
}