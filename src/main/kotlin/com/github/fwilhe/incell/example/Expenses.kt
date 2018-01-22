package com.github.fwilhe.incell.example

import com.github.fwilhe.incell.buildFunctionOf
import com.github.fwilhe.incell.spreadsheet

fun main(args: Array<String>) {
    val expenseList = listOf(55.3, 82.1, 2.8, 9.0)
    val expenses = buildFunctionOf(expenseList)
    fun shareOfExpense(x: Int): Double = expenses(x) / expenseList.sum() * 100
    fun expenseWithTaxes(x:Int): Double = expenses(x) * 1.15

    spreadsheet {
        column("Expenses", expenses)
        column("Share of Expense", ::shareOfExpense)
        column("Cost with (fictional) tax", ::expenseWithTaxes)
    }.print(expenseList.size)
}
