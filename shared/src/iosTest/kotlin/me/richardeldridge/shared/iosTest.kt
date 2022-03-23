package me.richardeldridge.shared

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting(username).contains("iOS"), "Check iOS is mentioned")
    }
}