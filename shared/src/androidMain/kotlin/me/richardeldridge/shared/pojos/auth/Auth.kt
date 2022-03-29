package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName

/**
 * @property data
 */
data class Auth(
        @SerializedName("data")
        var data: TokenData? = TokenData()
)
