package me.richardeldridge.shared.pojos.users

import com.google.gson.annotations.SerializedName


data class Data (
    @SerializedName("users")
    var users: ArrayList<Users> = arrayListOf(),
    @SerializedName("meta")
    var meta: Meta? = Meta()
)