package com.github.fwilhe.inzell

import kotlinx.html.*
import kotlinx.html.stream.createHTML

typealias columnFunction = (Int) -> Double

class Column(val title: String, private val function: columnFunction) {
    fun eval(i: Int): Double = function.invoke(i)
}

class Sheet(val columns: List<Column>, val caption: String = "(No caption provided)") {
    fun row(index: Int): List<Double> = columns.map { it.eval(index) }
    fun title(index: Int): String = columns[index].title
    fun forEachFunction(function: (Column) -> Unit) {
        columns.forEach {
            function(it)
        }
    }
}

abstract class SpreadsheetPrinter(val sheet: Sheet, val numberOfRows: Int = 10) {
    abstract override fun toString(): String
    fun printToStandardOut() {
        println(toString())
    }
}

class CsvPrinter(sheet: Sheet) : SpreadsheetPrinter(sheet) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(sheet.columns.joinToString(separator = ";") { column -> column.title }).append("\n")
        repeat(numberOfRows) { row ->
            stringBuilder.append(sheet.columns.map { column -> column.eval(row) }.joinToString(separator = ";"))
                .append("\n")
        }
        return stringBuilder.toString()
    }
}

class HtmlPrinter(sheet: Sheet) : SpreadsheetPrinter(sheet) {

    override fun toString(): String {
        return createHTML().table {
            caption { +sheet.caption }
            tr {
                repeat(sheet.columns.size) { colIndex ->
                    th {
                        +sheet.columns[colIndex].title
                    }
                }
            }
            repeat(10) { row ->
                tr {
                    sheet.columns.forEach {
                        td {
                            +it.eval(row).toString()
                        }
                    }
                }
            }
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