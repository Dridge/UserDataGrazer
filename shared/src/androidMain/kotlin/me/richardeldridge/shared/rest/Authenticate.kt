package me.richardeldridge.shared.rest.call

internal class Authenticate(username: String?, password: String?) {
    val username: String = "username from login"
    val password: String = "password from login"

    fun doAuthentication(username: String?, password: String?): Boolean {
        //do the authentication
        return true //or false...
    }
}