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

fun spreadsheet(): SpreadSheetBuilder {
    return SpreadSheetBuilder()

}

class SpreadSheetBuilder {

    private val columns = mutableListOf<Column>()

    fun addColumn(column: Column): SpreadSheetBuilder {
        columns.add(column)
        return this
    }

    fun addColumns(newColumns: List<Column>): SpreadSheetBuilder {
        columns.addAll(newColumns)
        return this
    }

    fun build(): Sheet {
        return Sheet(columns)
    }
}
