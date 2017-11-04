package com.github.fwilhe.incell

class Column(val title: String, private val function: (Int) -> Double) {
    fun eval(i: Int): Double {
        return function.invoke(i)
    }
}

class Sheet(private val numberOfRows: Int, private var columns: Array<Column>) {
    fun print() {
        columns.forEach { c -> print("${c.title};") }
        println()
        for (i in 0..(numberOfRows - 1)) {
            for (c in columns) {
                print("${c.eval(i)};")
            }
            println()
        }
    }
}

fun runSheet(columns: Array<Column>, numberOfRows: Int = 10) {
    val sheet = Sheet(numberOfRows, columns)
    sheet.print()
}