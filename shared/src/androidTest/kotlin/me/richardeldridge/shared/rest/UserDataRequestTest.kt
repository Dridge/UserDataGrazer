package me.richardeldridge.shared.rest

import kotlin.test.Test

class UserDataRequestTest {

    @Test
    fun testRestCallDoesNotThrowAnError() {
        val underTest = UserDataRetriever()
        underTest.getUsers("t5", "blah");
    }
}