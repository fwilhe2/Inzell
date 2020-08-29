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

        val s = spreadsheet {
            column("Expenses", expenses)
            column("Share of Expense", ::shareOfExpense)
            column("Cost with (fictional) tax", ::expenseWithTaxes)
        }
    }

    @Test
    fun printMarkdownTable() {
        val numberOfCpus = Column("Number of CPUs", ::powerOfTwo)
        val nX = Column("Problem Size X-Dimension") { 100.0 }
        val nY = Column("Problem Size Y-Dimension") { 100.0 }
        val tA = Column("Calculation Time per Cell") { 10.0 }
        val numberOfOperations = Column("Number of Operations") { 1.0 }
        val tK = Column("Communication Time per Cell") { 200.0 }
        fun timeParallel(x: Int): Double = (nX.eval(x) / numberOfCpus.eval(x)) * // Slice for each CPU
                nY.eval(x) * // Whole Y-Dimension of the problem
                tA.eval(x) * numberOfOperations.eval(x) + // Time to calculate each cell
                tK.eval(x) * numberOfCpus.eval(x) // Communication increases with number of CPUs

        val tP = Column("Parallel Time", ::timeParallel)
        fun timeSequential(x: Int): Double = nX.eval(x) * nY.eval(x) * tA.eval(x) * numberOfOperations.eval(x)
        val tS = Column("Sequential Time", ::timeSequential)
        fun calculateSpeedup(x: Int): Double = timeSequential(x) / timeParallel(x)
        val speedup = Column("Speedup", ::calculateSpeedup)
        fun calculateEfficiency(x: Int): Double = calculateSpeedup(x) / numberOfCpus.eval(x)
        val efficiency = Column("Efficiency", ::calculateEfficiency)

        val sheet = spreadsheet {
            caption("Performance model")
            add(numberOfCpus)
            add(nX)
            add(nY)
            add(tA)
            add(numberOfOperations)
            add(tK)
            add(tP)
            add(tS)
            add(speedup)
            add(efficiency)
        }


        MarkdownPrinter(sheet).printToStandardOut()
    }
}