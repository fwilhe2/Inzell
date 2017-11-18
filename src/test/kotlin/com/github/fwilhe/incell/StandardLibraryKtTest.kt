package com.github.fwilhe.incell

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StandardLibraryKtTest {
    @Test
    fun testIsEven() {
        assertEquals(1.0, isEven(0))
        assertEquals(0.0, isEven(1))
        assertEquals(1.0, isEven(2))
        assertEquals(0.0, isEven(3))
        assertEquals(1.0, isEven(4))
        assertEquals(0.0, isEven(5))
    }
}