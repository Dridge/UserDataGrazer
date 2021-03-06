package me.richardeldridge.shared.pojos.users

import com.google.gson.annotations.SerializedName

/**
 * @property name
 * @property dateOfBirth
 * @property profileImage
 */
data class Users(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("date_of_birth")
        var dateOfBirth: Int? = null,
        @SerializedName("profile_image")
        var profileImage: String? = null
)
