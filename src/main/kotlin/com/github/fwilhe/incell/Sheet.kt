package com.github.fwilhe.incell

class Column(val title: String, private val function: (Int) -> Double) {
    fun eval(i: Int): Double {
        return function.invoke(i)
    }
}

class Sheet(private val columns: List<Column>) {
    fun print(numberOfRows: Int = 10) {
        columns.forEach { c -> print("${c.title};") }
        println()
        for (i in 0..(numberOfRows - 1)) {
            for (c in columns) {
                print("${c.eval(i)};")
            }
            println()
        }
    }

    fun row(index: Int): List<Double> {
        return columns.map { it.eval(index) }
    }
}

fun spreadsheet(builder: Spreadsheet.() -> Unit): Sheet {
    val columns = mutableListOf<Column>()
    object : Spreadsheet {

        override fun column(title: String, function: (Int) -> Double) {
            columns.add(Column(title, function))
        }

        override fun add(column: Column) {
            columns.add(column)
        }

    }.builder()

    return Sheet(columns)
}

interface Spreadsheet {
    fun column(title: String, function: (Int) -> Double)
    fun add(column: Column)
}
