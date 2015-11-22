package com.github.fwilhe.incell

fun isEven(x: Int): Double = if (x.mod(2).equals(0)) 1.0 else 0.0
fun count(x: Int): Double = x.toDouble()

fun sine(x: Int): Double = Math.sin(x.toDouble())
fun cosine(x: Int): Double = Math.cos(x.toDouble())
fun tangent(x: Int): Double = Math.tan(x.toDouble())
fun logarithm(x: Int): Double = Math.log(x.toDouble())
fun absolute(x: Int): Double = Math.abs(x.toDouble())
fun random(x: Int): Double = Math.random()
fun powerOfTwo(x: Int): Double = Math.pow(x.toDouble(), 2.0)

fun extractFrom(function: (Int) -> Double, lowerIndex: Int, upperIndex: Int, results: List<Double> = arrayListOf()): DoubleArray {
    if (lowerIndex > upperIndex) return results.toDoubleArray()

    return com.github.fwilhe.incell.extractFrom(function, lowerIndex + 1, upperIndex, results + function(lowerIndex))
}
