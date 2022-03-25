package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName


data class Authenticate (

  @SerializedName("status")
  var status     : String? = null,
  @SerializedName("status_desc")
  var status_desc : String? = null,
  @SerializedName("auth")
  var auth       : Auth?   = Auth()

)