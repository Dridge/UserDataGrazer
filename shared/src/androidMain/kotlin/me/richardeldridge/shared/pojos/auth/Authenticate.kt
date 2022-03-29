package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName

/**
 * @property status
 * @property statusDesc
 * @property auth
 */
data class Authenticate(
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("status_desc")
        var statusDesc: String? = null,
        @SerializedName("auth")
        var auth: Auth? = Auth()
)
