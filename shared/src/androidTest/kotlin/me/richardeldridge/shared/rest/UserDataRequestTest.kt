package me.richardeldridge.shared.rest

import kotlin.test.Test

class UserDataRequestTest {

    @Test
    fun testRestCallDoesNotThrowAnError() {
        val underTest = UserDataRequest()
        underTest.getUsers("t5", "blah");
    }
}