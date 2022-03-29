package me.richardeldridge.shared.pojos.users

import com.google.gson.annotations.SerializedName

/**
 * @property status
 * @property statusDesc
 * @property data
 */
data class UserData(
        @SerializedName("status")
        var status: Int? = null,
        @SerializedName("status_desc")
        var statusDesc: String? = null,
        @SerializedName("data")
        var data: Data? = Data()
)
