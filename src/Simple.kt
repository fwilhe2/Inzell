package net.utnik.sp.incell.demo.simple

import net.utnik.sp.incell.Column
import net.utnik.sp.incell.runSheet

/**
 * Created by sputnik on 08.02.15.
 */

fun count(x: Int): Double {
    return x * 1.0 //TODO Another way to cast to double?
}

fun main(args: Array<String>) {
    val countUp: Column = Column("Count", ::count)
    val constantValue: Column = Column("Constant Value", { 42.23 })

    runSheet(array(countUp, constantValue))
}