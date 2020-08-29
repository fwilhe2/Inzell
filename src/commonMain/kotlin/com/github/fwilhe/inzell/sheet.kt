package com.github.fwilhe.inzell


typealias columnFunction = (Int) -> Any

class Column(val title: String, private val function: columnFunction) {
    fun eval(i: Int): Any = function.invoke(i)
}

class Sheet(val columns: List<Column>, val caption: String = "(No caption provided)") {
    fun row(index: Int): List<Any> = columns.map { it.eval(index) }
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
        for (row in 1..numberOfRows){
            stringBuilder.append(sheet.columns.map { column -> column.eval(row) }.joinToString(separator = ";"))
                .append("\n")
        }
        return stringBuilder.toString()
    }
}

class MarkdownPrinter(sheet: Sheet) : SpreadsheetPrinter(sheet) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(sheet.columns.joinToString(separator = " | ") { column -> column.title }).append("\n")
        stringBuilder.append(sheet.columns.joinToString(separator = " | ") { column -> "-".repeat(column.title.length) })
            .append("\n")
        for (row in 1..numberOfRows){
            stringBuilder.append(sheet.columns.map { column -> column.eval(row).toString().padEnd(column.title.length) }
                .joinToString(separator = " | "))
                .append("\n")
        }
        return stringBuilder.toString()
    }
}

class HtmlPrinter(sheet: Sheet) : SpreadsheetPrinter(sheet) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendLine("<table>")
        stringBuilder.appendLine("  <caption>${sheet.caption}</caption>")
        stringBuilder.appendLine("  <tr>")
        repeat(sheet.columns.size) { colIndex ->
            stringBuilder.appendLine("    <th>${sheet.columns[colIndex].title}</th>")
        }
        stringBuilder.appendLine("  </tr>")
        for (row in 1..numberOfRows){
            stringBuilder.appendLine("  <tr>")
            sheet.columns.forEach {
                stringBuilder.appendLine("    <td>${it.eval(row)}</td>")
            }
            stringBuilder.appendLine("  </tr>")
        }
        stringBuilder.appendLine("</table>")
        return stringBuilder.toString()
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
fun buildFunctionOf(values: Array<Any>): columnFunction = { i: Int ->
    values[i]
}

fun buildFunctionOf(values: Collection<Any>): columnFunction = { i: Int ->
    values.elementAt(i)
}