package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName

/**
 * @property token
 */
data class TokenData(
        @SerializedName("token")
        var token: String? = null
)
