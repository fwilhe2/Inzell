package com.github.fwilhe.inzell

import kotlin.test.Test
import kotlin.test.assertEquals

class SheetsTest {
    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value") { 1.11 })).row(0)
        val sheet = spreadsheet { column("constant value") { 1.11 } }.row(0)
        assertEquals(expected, sheet)
    }

    @Test
    fun expenses() {
        val expenseList = listOf(55.3, 82.1, 2.8, 9.0)
        val expenses = buildFunctionOf(expenseList)
        fun shareOfExpense(x: Int): Double = expenses(x) / expenseList.sum() * 100
        fun expenseWithTaxes(x: Int): Double = expenses(x) * 1.15

        spreadsheet {
            column("Expenses", expenses)
            column("Share of Expense", ::shareOfExpense)
            column("Cost with (fictional) tax", ::expenseWithTaxes)
        }.print(expenseList.size)
    }
}