@file:JvmName("StandardLibrary")

package com.github.fwilhe.inzell

import kotlin.jvm.JvmName
import kotlin.math.pow
import kotlin.random.*

fun isEven(x: Int): Double = if (x.rem(2) == 0) 1.0 else 0.0
fun count(x: Int): Double = x.toDouble()

fun sine(x: Int): Double = kotlin.math.sin(x.toDouble())
fun cosine(x: Int): Double = kotlin.math.cos(x.toDouble())
fun tangent(x: Int): Double = kotlin.math.tan(x.toDouble())
fun logarithm(x: Int): Double = kotlin.math.ln(x.toDouble())
fun absolute(x: Int): Double = kotlin.math.abs(x.toDouble())
fun powerOfTwo(x: Int): Double = x.toDouble().pow(2)
fun random(x: Int): Double = Random.Default.nextDouble()