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
    spreadsheet()
            .addColumn(Column("Count", ::count))
            .addColumn(Column("Constant Value", { 42.23 }))
            .addColumn(Column("Arbitrary Value", ::arbitrary))
            .addColumn(Column("Even Value", ::isEven))
            .addColumn(Column("sin()", ::sine))
            .addColumn(Column("cos()", ::cosine))
            .addColumn(Column("tan()", ::tangent))
            .addColumn(Column("log()", ::logarithm))
            .addColumn(Column("abs()", ::absolute))
            .addColumn(Column("Random", ::random))
            .build()
            .print(3)
}
