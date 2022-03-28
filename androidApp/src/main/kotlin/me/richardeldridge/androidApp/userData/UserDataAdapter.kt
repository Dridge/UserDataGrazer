package me.richardeldridge.androidApp.userData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.richardeldridge.androidApp.R
import me.richardeldridge.shared.pojos.users.Users
import me.richardeldridge.shared.rest.authentication.Authenticator
import me.richardeldridge.shared.rest.authentication.IAuthenticationObserver

/**
 * Based on the tutorial explained here: https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin
 */
class UserDataAdapter(private val context: Context, private val dataSource: ArrayList<Users>) : IAuthenticationObserver, BaseAdapter() {
    init { //TODO why does this need to be an observer?
        Authenticator.add(this)
    }
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
        val view: View
        val holder: MyViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_user, parent, false)
            holder = MyViewHolder()
            holder.nameTextView = view.findViewById(R.id.name) as TextView
            holder.dateOfBirthTextView = view.findViewById(R.id.date_of_birth) as TextView
            holder.userPhotoImageView = view.findViewById(R.id.user_photo) as ImageView
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as MyViewHolder
        }
        val nameTextView = holder.nameTextView
        val dateOfBirthTextView = holder.dateOfBirthTextView
        val userPhotoImageView = holder.userPhotoImageView
        val user = getItem(position) as Users

        nameTextView.text = user.name
        dateOfBirthTextView.text = user.dateOfBirth.toString()

        Picasso.with(context).load(user.profileImage).placeholder(R.mipmap.ic_launcher).into(userPhotoImageView)
        return view
    }

    private class MyViewHolder {
        lateinit var nameTextView: TextView
        lateinit var dateOfBirthTextView: TextView
        lateinit var userPhotoImageView: ImageView
    }

    override fun update() {

    }
}