package com.github.fwilhe.incell.example

import com.github.fwilhe.incell.*

fun arbitrary(x: Int): Double {
    return when (x) {
        in 0..3 -> 42.0
        in 5..7 -> 2.4
        else -> 1.0
    }
}

fun main(args: Array<String>) {
    val countUp: Column = Column("Count", ::count)
    val constantValue: Column = Column("Constant Value", { 42.23 })
    val arbitraryValue: Column = Column("Arbitrary Value", ::arbitrary)
    val evenValue: Column = Column("Even Value", ::isEven)
    val sine: Column = Column("sin()", ::sine)
    val cosine: Column = Column("cos()", ::cosine)
    val tangent: Column = Column("tan()", ::tangent)
    val logarithm: Column = Column("log()", ::logarithm)
    val absolute: Column = Column("abs()", ::absolute)
    val random: Column = Column("Random", ::random)

    runSheet(arrayOf(countUp, constantValue, arbitraryValue, evenValue, sine, cosine, tangent, logarithm, absolute, random))
}