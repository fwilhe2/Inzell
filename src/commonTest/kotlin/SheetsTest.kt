package com.github.fwilhe.inzell

import kotlinx.datetime.Clock
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
    fun printTables() {
        val numberOfCpus = Column("Number of CPUs") { x -> x * x }
        val nX = Column("Problem Size X-Dimension") { 100 }
        val nY = Column("Problem Size Y-Dimension") { 100 }
        val tA = Column("Calculation Time per Cell") { 10 }
        val numberOfOperations = Column("Number of Operations") { 1 }
        val tK = Column("Communication Time per Cell") { 200 }
        fun timeParallel(x: Int): Int = (nX.evalInt(x) / numberOfCpus.evalInt(x) as Int) * // Slice for each CPU
                nY.evalInt(x) * // Whole Y-Dimension of the problem
                tA.evalInt(x) * numberOfOperations.evalInt(x) + // Time to calculate each cell
                tK.evalInt(x) * numberOfCpus.evalInt(x) // Communication increases with number of CPUs

        val tP = Column("Parallel Time", ::timeParallel)
        fun timeSequential(x: Int): Int = nX.evalInt(x) * nY.evalInt(x) * tA.evalInt(x) * numberOfOperations.evalInt(x)
        val tS = Column("Sequential Time", ::timeSequential)
        fun calculateSpeedup(x: Int): Double = timeSequential(x) / timeParallel(x).toDouble()
        val speedup = Column("Speedup", ::calculateSpeedup)
        fun calculateEfficiency(x: Int): Double = calculateSpeedup(x) / numberOfCpus.evalInt(x)
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