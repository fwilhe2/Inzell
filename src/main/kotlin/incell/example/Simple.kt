package incell.example

import incell.*

fun arbitrary(x: Int): Double {
    when (x) {
        in 0..3 -> return 42.0
        in 5..7 -> return 2.4
        else -> return 1.0
    }
}

fun sum(vararg arguments: Double): Double {
    return arguments.sum().toDouble()
}

fun main(args: Array<String>) {
    val countUp: Column = Column("Count", ::count)
    val constantValue: Column = Column("Constant Value", { 42.23 })
    val arbitraryValue: Column = Column("Arbitrary Value", ::arbitrary)
    val evenValue: Column = Column("Even Value", ::isEven)
    val sine: Column = Column("sin()", ::sine)
    val cosine: Column = Column("cos()", ::cosine)
    val random: Column = Column("Random", ::random)

    val someValues: DoubleArray = extractFrom(::count, 3, 6)

    runSheet(arrayOf(countUp, constantValue, arbitraryValue, evenValue, sine, cosine, random))
}