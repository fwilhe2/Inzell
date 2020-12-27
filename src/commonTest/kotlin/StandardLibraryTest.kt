package com.github.fwilhe.inzell

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StandardLibraryTest {
    @Test
    fun even() {
        assertTrue(isEven(0))
        assertFalse(isEven(1))
        assertTrue(isEven(2))
        assertFalse(isEven(3))
    }

    @Test
    fun prime() {
        assertFalse(isPrime(0))
        assertFalse(isPrime(1))
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertFalse(isPrime(4))
        assertTrue(isPrime(5))
        assertFalse(isPrime(6))
        assertTrue(isPrime(7))
        assertFalse(isPrime(8))
    }
}