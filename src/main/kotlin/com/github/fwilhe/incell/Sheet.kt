package com.github.fwilhe.incell

class Column(val title: String, private val function: (Int) -> Double) {
    fun eval(i: Int): Double {
        return function.invoke(i)
    }
}

class Sheet(private val columns: List<Column>) {
    fun print(numberOfRows: Int = 10) {
        println(columns.joinToString(separator = ";") { column -> column.title })
        repeat(numberOfRows) { row ->
            println(columns.map { column -> column.eval(row) }.joinToString(separator = ";"))
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
