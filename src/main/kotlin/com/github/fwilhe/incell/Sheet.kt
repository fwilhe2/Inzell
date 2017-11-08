package com.github.fwilhe.incell

class Column(val title: String, private val function: (Int) -> Double) {
    fun eval(i: Int): Double {
        return function.invoke(i)
    }
}

class Sheet(private val numberOfRows: Int, private val columns: List<Column>) {
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
        return Sheet(10, columns)
    }
}
