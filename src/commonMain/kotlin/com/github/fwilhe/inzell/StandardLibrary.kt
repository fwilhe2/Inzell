@file:JvmName("StandardLibrary")

package com.github.fwilhe.inzell

import kotlin.jvm.JvmName
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.*

fun isEven(x: Int): Boolean = x.rem(2) == 0

fun isPrime(x: Int): Boolean {
    if (x < 2) {
        return false
    }

    for (i in 2..sqrt(x.toDouble()).toInt()){
        if (x.rem(i) == 0) {
            return false
        }
    }
    return true
}

fun count(x: Int): Int = x

fun sine(x: Int): Double = kotlin.math.sin(x.toDouble())
fun cosine(x: Int): Double = kotlin.math.cos(x.toDouble())
fun tangent(x: Int): Double = kotlin.math.tan(x.toDouble())
fun logarithm(x: Int): Double = kotlin.math.ln(x.toDouble())
fun absolute(x: Int): Double = kotlin.math.abs(x.toDouble())
fun powerOfTwo(x: Int): Double = x.toDouble().pow(2)
fun random(x: Int): Double = Random.Default.nextDouble()