package me.richardeldridge.shared.pojos.auth

import com.google.gson.annotations.SerializedName


data class Auth (
  @SerializedName("data")
  var data : TokenData? = TokenData()

)