package com.github.fwilhe.inzell

import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals

class SheetsTest {

    @Test
    fun typeSystem() {
        fun a(x: Int): Any = 1
        println(::a::toString)
    }

    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value") { 1.11 })).row(0)
        val sheet = spreadsheet { column("constant value") { 1.11 } }.row(0)
        assertEquals(expected, sheet)
    }

    @Test
    fun printTables() {
        val numberOfCpus = Column("Number of CPUs") { x -> x * x }
        val nX = Column("Problem Size X-Dimension") { 100 }
        val nY = Column("Problem Size Y-Dimension") { 100 }
        val tA = Column("Calculation Time per Cell") { 10 }
        val numberOfOperations = Column("Number of Operations") { 1 }
        val tK = Column("Communication Time per Cell") { 200 }
        fun timeParallel(x: Int): Int = (nX.eval(x) as Int / numberOfCpus.eval(x) as Int) * // Slice for each CPU
                nY.eval(x) as Int * // Whole Y-Dimension of the problem
                tA.eval(x) as Int * numberOfOperations.eval(x) as Int + // Time to calculate each cell
                tK.eval(x) as Int * numberOfCpus.eval(x) as Int // Communication increases with number of CPUs

        val tP = Column("Parallel Time", ::timeParallel)
        fun timeSequential(x: Int): Int = nX.eval(x) as Int * nY.eval(x) as Int * tA.eval(x) as Int * numberOfOperations.eval(x) as Int
        val tS = Column("Sequential Time", ::timeSequential)
        fun calculateSpeedup(x: Int): Double = timeSequential(x) / timeParallel(x).toDouble()
        val speedup = Column("Speedup", ::calculateSpeedup)
        fun calculateEfficiency(x: Int): Double = calculateSpeedup(x) / numberOfCpus.eval(x) as Int
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
        CsvPrinter(sheet).printToStandardOut()
        MarkdownPrinter(sheet).printToStandardOut()
        HtmlPrinter(sheet).printToStandardOut()
    }
}