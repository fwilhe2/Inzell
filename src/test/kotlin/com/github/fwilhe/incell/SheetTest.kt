package com.github.fwilhe.incell

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SheetTest {
    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value", { 1.11 }))).row(0)
        val sheet = spreadsheet { column("constant value", { 1.11 }) }.row(0)
        assertEquals(expected, sheet)
    }
}