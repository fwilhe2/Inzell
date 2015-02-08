package net.utnik.sp.incell

/**
 * Created by sputnik on 08.02.15.
 */

class Column(val title: String, val content: (Int) -> Double) {
    fun eval(i: Int): Double {
        return content.invoke(i)
    }
}

class Sheet(val numberOfRows: Int, var content: Array<Column>) {
    fun print() {
        for (c in content) {
            print("${c.title};")
        }
        println()
        for (i in 0..(numberOfRows - 1)) {
            //TODO what if f(0) is not defined?
            for (c in content) {
                print("${c.eval(i)};")
            }
            println()
        }
    }
}

fun runSheet(columns :Array<Column>, numberOfRows: Int = 10) {
    val sheet = Sheet(numberOfRows, columns)
    sheet.print()
}