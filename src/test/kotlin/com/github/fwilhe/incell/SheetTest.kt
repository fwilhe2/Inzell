package com.github.fwilhe.incell

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SheetTest {
    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value", { 1.11 }))).row(0)
        val sheet = spreadsheet { column("constant value", { 1.11 }) }.row(0)
        assertEquals(expected, sheet)
    }


    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    fun functionOfArray(i: Int) {

        fun expected(x: Int): Double = when (x) {
            0 -> 2.3
            1 -> 1.3
            2 -> 7.2
            else -> throw RuntimeException("Test failed, actual array/list has only 3 entries.")
        }

        val actualArray: columnFunction = buildFunctionOf(arrayOf(2.3, 1.3, 7.2))
        val actualList: columnFunction = buildFunctionOf(listOf(2.3, 1.3, 7.2))

        assertEquals(expected(i), actualArray(i))
        assertEquals(expected(i), actualList(i))
    }
}