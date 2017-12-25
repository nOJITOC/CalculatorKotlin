package com.mmteams91.calculator.core.calculate

import android.support.annotation.VisibleForTesting
import java.util.*

/**
 * Created by User New on 19.12.2017.
 */
enum class Operator {
    Plus,

    Minus,

    Times,

    Div,

    NoOperator;

    @VisibleForTesting companion object {
        private val operators = "+-*/"
        fun random(): Operator {
            val size = Operator.values().size - 1
            val index = Random().nextInt(16) % size
            val arr = Operator.values()
            val operator = Operator.values()[index]
            return operator
        }

        fun valueOf(char: Char): Operator = when (char) {
            '+' -> Plus
            '-' -> Minus
            '*' -> Times
            '/' -> Div
            else -> NoOperator
        }

        fun isOperator(check: Char): Boolean = operators.contains(check)
    }

    fun isOperator(): Boolean = this != NoOperator


    override fun toString(): String = when (this) {
        Operator.Plus -> "+"
        Operator.Minus -> "-"
        Operator.Div -> "/"
        Operator.Times -> "*"
        Operator.NoOperator -> ""
    }

    fun toChar(): Char = when (this) {
        Operator.Plus -> '+'
        Operator.Minus -> '-'
        Operator.Div -> '/'
        Operator.Times -> '*'
        Operator.NoOperator -> ' '
    }

    fun getPriority(): Int = when (this) {
        Operator.Plus -> 1
        Operator.Minus -> 1
        Operator.Div -> 2
        Operator.Times -> 2
        Operator.NoOperator -> 0
    }


}