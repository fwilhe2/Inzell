package net.utnik.sp.incell

/**
 * Created by sputnik on 07.02.15.
 */
class Column(val title: String, val content: (Int) -> Double) {
    fun eval(i: Int): Double {
        return content.invoke(i)
    }
}

class Sheet(var content: List<Column>) {
    fun print() {

        for (c in content) {
            print("\t\t${c.title}")
        }

        println()

        for (i in 0..10) {
            for (c in content) {

                print("\t\t${c.eval(i)}")
            }
            println()
        }
    }
}

fun powerOfTwo(x: Int): Double {
    if (x == 0)
        return 1.0;
    else
        return powerOfTwo(x - 1) * 2.0
}


fun main(args: Array<String>) {

    var numberOfCpus: Column = Column("Number of CPUs", ::powerOfTwo)

    var nX: Column = Column("Problem Size X-Dimension", { 100.0 })
    var nY: Column = Column("Problem Size Y-Dimension", { 100.0 })
    var tA: Column = Column("Calculation Time per Cell", { 10.0 })
    var numberOfOperations: Column = Column("Number of Operations", { 1.0 })
    var tK: Column = Column("Communication Time per Cell", { 200.0 })

    fun timeParallel(x: Int): Double {
        return (nX.eval(x) / numberOfCpus.eval(x)) * // Slice for each CPU
                nY.eval(x) * // Whole Y-Dimension of the problem
                tA.eval(x) * numberOfOperations.eval(x) + // Time to calculate each cell
                tK.eval(x) * numberOfCpus.eval(x) // Communication increases with number of CPUs
    }


    var tP: Column = Column("Parallel Time", ::timeParallel)

    fun timeSequential(x: Int): Double {
        return nX.eval(x) * nY.eval(x) * tA.eval(x) * numberOfOperations.eval(x)
    }

    var tS: Column = Column("Sequential Time", ::timeSequential)

    fun calculateSpeedup(x: Int): Double {
        return timeSequential(x) / timeParallel(x)
    }

    fun calculateEfficiency(x: Int): Double {
        return calculateSpeedup(x) / numberOfCpus.eval(x)
    }

    var speedup: Column = Column("Speedup", ::calculateSpeedup)
    var efficiency: Column = Column("Efficiency", ::calculateEfficiency)

    var sheet = Sheet(linkedListOf(numberOfCpus, nX, nY, tA, numberOfOperations, tK, tP, tS, speedup, efficiency))
    sheet.print()
}