import com.github.fwilhe.inzell.*

fun main() {
    val numberOfCpus = Column("Number of CPUs", ::powerOfTwo)
    val nX = Column("Problem Size X-Dimension") { 100.0 }
    val nY = Column("Problem Size Y-Dimension") { 100.0 }
    val tA = Column("Calculation Time per Cell") { 10.0 }
    val numberOfOperations = Column("Number of Operations") { 1.0 }
    val tK = Column("Communication Time per Cell") { 200.0 }
    fun timeParallel(x: Int): Double = (nX.eval(x) / numberOfCpus.eval(x)) * // Slice for each CPU
            nY.eval(x) * // Whole Y-Dimension of the problem
            tA.eval(x) * numberOfOperations.eval(x) + // Time to calculate each cell
            tK.eval(x) * numberOfCpus.eval(x) // Communication increases with number of CPUs

    val tP = Column("Parallel Time", ::timeParallel)
    fun timeSequential(x: Int): Double = nX.eval(x) * nY.eval(x) * tA.eval(x) * numberOfOperations.eval(x)
    val tS = Column("Sequential Time", ::timeSequential)
    fun calculateSpeedup(x: Int): Double = timeSequential(x) / timeParallel(x)
    val speedup = Column("Speedup", ::calculateSpeedup)
    fun calculateEfficiency(x: Int): Double = calculateSpeedup(x) / numberOfCpus.eval(x)
    val efficiency = Column("Efficiency", ::calculateEfficiency)

    val sheet = spreadsheet {
        caption("Performance model")
        add(numberOfCpus)
        add(nX)
        add(nY)
        add(tA)
        add(numberOfOperations)
        add(tK)
        add(tP)
        add(tS)
        add(speedup)
        add(efficiency)
    }


    CsvPrinter(sheet).printToStandardOut()
    HtmlPrinter(sheet).printToStandardOut()

}