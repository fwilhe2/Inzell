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
    spreadsheet {
        column("Count", ::count)
        column("Constant Value", { 10.0 })
        column("Arbitrary Value", ::arbitrary)
        column("Is even", ::isEven)
        column("abs()", ::absolute)
        column("Random", ::random)
    }.print(3)

    val average = spreadsheet {
        column("sin()", ::sine)
        column("cos()", ::cosine)
        column("tan()", ::tangent)
    }.row(6).average()
    println("The average is $average")
}

