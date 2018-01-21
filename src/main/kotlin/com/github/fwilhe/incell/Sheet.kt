package com.github.fwilhe.incell

typealias columnFunction = (Int) -> Double

class Column(val title: String, private val function: columnFunction) {
    fun eval(i: Int): Double = function.invoke(i)
}

class Sheet(private val columns: List<Column>) {
    fun print(numberOfRows: Int = 10) {
        println(columns.joinToString(separator = ";") { column -> column.title })
        repeat(numberOfRows) { row ->
            println(columns.map { column -> column.eval(row) }.joinToString(separator = ";"))
        }
    }

    fun row(index: Int): List<Double> = columns.map { it.eval(index) }
}

fun spreadsheet(builder: Spreadsheet.() -> Unit): Sheet {
    val columns = mutableListOf<Column>()
    object : Spreadsheet {

        override fun column(title: String, function: columnFunction) {
            columns.add(Column(title, function))
        }

        override fun add(column: Column) {
            columns.add(column)
        }

    }.builder()

    return Sheet(columns)
}

interface Spreadsheet {
    fun column(title: String, function: columnFunction)
    fun add(column: Column)
}

//todo handle case when i >= values.size()
fun fromArray(values: Array<Double>): columnFunction = { i: Int ->
    values[i]
}