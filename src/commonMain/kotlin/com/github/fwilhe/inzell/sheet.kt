package com.github.fwilhe.inzell

typealias columnFunction = (Int) -> Double

class Column(val title: String, private val function: columnFunction) {
    fun eval(i: Int): Double = function.invoke(i)
}

class Sheet(private val columns: List<Column>, val caption: String = "(No caption provided)") {
    fun print(numberOfRows: Int = 10) {
        println(columns.joinToString(separator = ";") { column -> column.title })
        repeat(numberOfRows) { row ->
            println(columns.map { column -> column.eval(row) }.joinToString(separator = ";"))
        }
    }

    fun row(index: Int): List<Double> = columns.map { it.eval(index) }
    fun title(index: Int): String = columns[index].title
    fun forEachFunction(function: (Column) -> Unit) {
        columns.forEach {
            function(it)
        }
    }
}

fun spreadsheet(builder: Spreadsheet.() -> Unit): Sheet {
    val columns = mutableListOf<Column>()
    var theCaption: String = "(No caption provided)"
    object : Spreadsheet {
        override fun caption(caption: String) {
            theCaption = caption
        }

        override fun column(title: String, function: columnFunction) {
            columns.add(Column(title, function))
        }

        override fun add(column: Column) {
            columns.add(column)
        }

    }.builder()

    return Sheet(columns, theCaption)
}

interface Spreadsheet {
    fun caption(caption: String)
    fun column(title: String, function: columnFunction)
    fun add(column: Column)
}

//todo handle case when i >= values.size()
fun buildFunctionOf(values: Array<Double>): columnFunction = { i: Int ->
    values[i]
}

fun buildFunctionOf(values: Collection<Double>): columnFunction = { i: Int ->
    values.elementAt(i)
}