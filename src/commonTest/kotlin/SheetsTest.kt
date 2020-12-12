package com.github.fwilhe.inzell

import kotlinx.datetime.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class SheetsTest {
    @Test
    fun spreadsheetBuilder() {
        val expected = Sheet(listOf(Column("constant value") { 1.11 })).row(0)
        val sheet = spreadsheet { column("constant value") { 1.11 } }.row(0)
        assertEquals(expected, sheet)
    }

    @ExperimentalTime
    @Test
    fun dateCalculations() {
        fun f(x: Int): LocalDateTime {
            return when (x) {
                1 -> LocalDateTime(2016, 2, 15, 16, 57, 0, 0)
                2 -> LocalDateTime(2017, 4, 15, 16, 7, 0, 0)
                3 -> LocalDateTime(2018, 2, 20, 16, 5, 0, 0)
                4 -> LocalDateTime(2019, 2, 15, 1, 57, 0, 0)
                5 -> LocalDateTime(2020, 2, 15, 16, 5, 0, 0)
                6 -> LocalDateTime(2021, 2, 15, 16, 22, 0, 0)
                else -> LocalDateTime(0, 1, 1, 0, 0, 0, 0)
            }
        }

        fun g(x: Int): Instant {
            val now = Clock.System.now()
            val instantInThePast: Instant = Instant.parse("2020-01-01T00:00:00Z")
            val durationSinceThen: Duration = now - instantInThePast
            return f(x).toInstant(TimeZone.UTC) + durationSinceThen
        }

        fun h(x: Int): Duration {
            return (f(x).toInstant(TimeZone.UTC) - g(x)).absoluteValue
        }

        val sheet = spreadsheet {
            add(Column("Date", ::f))
            add(Column("Future with added duration", ::g))
            add(Column("Duration between", ::h))
        }

        MarkdownPrinter(sheet).printToStandardOut()
    }

    @Test
    fun booleanValues() {
        val sheet = spreadsheet {
            column("i") { x -> count(x) }
            column("is even") { x -> isEven(x) }
            column("even and prime") { x -> isEven(x).and(isPrime(x)) }
        }

        MarkdownPrinter(sheet).printToStandardOut()

    }

    @Test
    fun printTables() {
        val numberOfCpus = Column("Number of CPUs") { x -> x * x }
        val nX = Column("Problem Size X-Dimension") { 100 }
        val nY = Column("Problem Size Y-Dimension") { 100 }
        val tA = Column("Calculation Time per Cell") { 10 }
        val numberOfOperations = Column("Number of Operations") { 1 }
        val tK = Column("Communication Time per Cell") { 200 }
        fun timeParallel(x: Int): Int = (nX.evalInt(x) / numberOfCpus.evalInt(x)) * // Slice for each CPU
                nY.evalInt(x) * // Whole Y-Dimension of the problem
                tA.evalInt(x) * numberOfOperations.evalInt(x) + // Time to calculate each cell
                tK.evalInt(x) * numberOfCpus.evalInt(x) // Communication increases with number of CPUs

        val tP = Column("Parallel Time", ::timeParallel)
        fun timeSequential(x: Int): Int =
            nX.evalInt(x) * nY.evalInt(x) * tA.evalInt(x) * numberOfOperations.evalInt(x)

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
