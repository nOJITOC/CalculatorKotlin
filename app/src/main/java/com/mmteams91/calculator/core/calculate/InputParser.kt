package com.mmteams91.calculator.core.calculate

import java.util.regex.Pattern


/**
 * Created by User New on 20.12.2017.
 */
class InputParser {
    val numPattern: Pattern = Pattern.compile("[\\-]?\\d+\\.?\\d*")
    val splitPattern: Pattern = Pattern.compile("[\\-]?\\d+\\.?\\d*[+\\-/*]?")


    fun parse(total: String, input: Char): Calculate = when {
        Operator.isOperator(input) -> parse(total)
        else -> parse(total + input)
    }

    fun parse(total: String): Calculate = when {
        total.isEmpty() -> Value(0.0)
        else -> Value(total.toDouble())

    }
}