package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName


data class TokenData (

  @SerializedName("token")
  var token : String? = null

)