package com.mmteams91.calculator.core.calculate

import java.util.*

/**
 * Created by User New on 20.12.2017.
 */
fun Random.nextDigit(): Int = this.nextInt(16) % 10

fun Random.nextValue(): Value = Value(this.nextDouble())
fun Random.nextOperator(): Operator = Operator.random()
fun Random.nextSimpleOperation(): Operation = Operation(this.nextValue(), this.nextOperator(), this.nextValue())
fun Int.toDigit(): Char = when (this) {
    0 -> '0'
    1 -> '1'
    2 -> '2'
    3 -> '3'
    4 -> '4'
    5 -> '5'
    6 -> '6'
    7 -> '7'
    8 -> '8'
    9 -> '9'
    else -> throw IllegalArgumentException("$this not a digit")
}

fun Char.toDigitInt(): Int = when (this) {
    '0' -> 0
    '1' -> 1
    '2' -> 2
    '3' -> 3
    '4' -> 4
    '5' -> 5
    '6' -> 6
    '7' -> 7
    '8' -> 8
    '9' -> 9
    else -> throw IllegalArgumentException("$this not a digit")
}

