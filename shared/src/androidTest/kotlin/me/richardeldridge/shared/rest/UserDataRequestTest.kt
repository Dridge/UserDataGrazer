package me.richardeldridge.shared.rest

import kotlin.test.Test

class UserDataRequestTest {
    @Test
    fun testGetUsersDoesNotThrowAnError() {
        val underTest = UserDataRetriever.getInstance()
        underTest.populateUserData();
    }
}
