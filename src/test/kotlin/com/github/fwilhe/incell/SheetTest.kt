package com.github.fwilhe.incell

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SheetTest {
    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value") { 1.11 })).row(0)
        val sheet = spreadsheet { column("constant value") { 1.11 } }.row(0)
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

    @Test
    fun performanceModel() {
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

        spreadsheet {
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
    }

    @Test
    fun simpleSpreadsheet() {
        fun arbitrary(x: Int): Double = when (x) {
            in 0..3 -> 42.0
            in 5..7 -> 2.4
            else -> 1.0
        }
        spreadsheet {
            column("Count", ::count)
            column("Constant Value") { 10.0 }
            column("Arbitrary Value", ::arbitrary)
            column("Is even", ::isEven)
            column("abs()", ::absolute)
            column("From Array", buildFunctionOf(arrayOf(1.0, 2.0, 3.0)))
            column("From List", buildFunctionOf(listOf(1.0, 2.0, 3.0)))
        }.print(3)

        val average = spreadsheet {
            column("sin()", ::sine)
            column("cos()", ::cosine)
            column("tan()", ::tangent)
        }.row(6).average()
        println("The average is $average")
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
