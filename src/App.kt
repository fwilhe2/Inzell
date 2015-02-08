package net.utnik.sp.incell

/**
 * Created by sputnik on 07.02.15.
 */

data abstract class Cell() {
    abstract fun evaluate(): Double
}

data class CellX (val content: () -> Double) : Cell() {
    override fun evaluate(): Double {
        return content.invoke()
    }
}

data class CellY(val content: (Double, Double) -> Double) : Cell() {
    override fun evaluate(): Double {
        return content.invoke(.1, .2)
    }
}

data class Column(val content: List<Cell>)

data class Sheet(var content: List<Column>) {

    fun evaluate() {
        for (column in content) {
            for (cell in column.content) {
                print("${cell.evaluate()}\t")
            }
            println()
        }
    }

}

fun add(): (Double, Double) -> Double = {(x: Double, y: Double): Double -> x + y }

fun main(args: Array<String>) {

    var numberOfCpus: Column = Column(linkedListOf(CellX({ 1.0 }), CellX({ 2.0 }))) // todo function for power of two
    var nX: Column = Column(linkedListOf(CellX({ 100.0 })))
    var nY: Column = Column(linkedListOf(CellX({ 100.0 })))
    var tA: Column = Column(linkedListOf(CellX({ 10.0 })))
    var numberOfOperations: Column = Column(linkedListOf(CellX({ 5.0 })))
    var tK: Column = Column(linkedListOf(CellX({ 200.0 })))
    var tP: Column = Column(linkedListOf())
    var tS: Column = Column(linkedListOf())
    var speedup: Column = Column(linkedListOf())
    var effizienz: Column = Column(linkedListOf())
    //var b: Column = Column(linkedListOf(CellY(add())))

    var sheet = Sheet(linkedListOf(numberOfCpus, nX, nY, tA, numberOfOperations, tK, tP, tS, speedup, effizienz))

    println(sheet)

    sheet.evaluate()
}