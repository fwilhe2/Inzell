package net.utnik.sp.incell.demo.simple

import net.utnik.sp.incell.Column
import net.utnik.sp.incell.runSheet

/**
 * Created by sputnik on 08.02.15.
 */

fun count(x: Int): Double {
    return x * 1.0 //TODO Another way to cast to double?
}

fun arbitrary(x: Int): Double {
    when (x) {
        in 0..3 -> return 42.0
        in 5..7 -> return 2.4
        else -> return 1.0
    }
}

fun onlyWhenEven(x: Int): Double {
    when {
        x % 2 == 0 -> return 1.0
        x % 2 == 1 -> return 0.0
    }
    return -1.0
}

fun main(args: Array<String>) {
    val countUp: Column = Column("Count", ::count)
    val constantValue: Column = Column("Constant Value", { 42.23 })
    val arbitraryValue: Column = Column("Arbitrary Value", ::arbitrary)
    val evenValue: Column = Column("Even Value", ::onlyWhenEven)

    runSheet(array(countUp, constantValue, arbitraryValue, evenValue))
}