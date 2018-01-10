package com.mmteams91.calculator.core.calculate

import java.text.DecimalFormat

/**
 * Created by User New on 20.12.2017.
 */
class Calculator(private val inputParser: InputParser = InputParser()) {
    companion object {
        val DECIMAL_SEPARATOR = '.'
        val LEFT_BRACE = '('
        val RIGHT_BRACE = ')'
        val ZERO = '0'
    }

    var calculate: Calculate = NoValue()
    var currentOperation: String = ""
    var operator = Operator.NoOperator
    var inner: Calculator? = null


    fun append(input: Char): Calculator {
        inner?.append(input) ?: if (input == LEFT_BRACE) {
            if (operator != Operator.NoOperator)
                inner = Calculator()
        } else if (Operator.isOperator(input)) {
            if (!currentOperation.isEmpty() || calculate is NoValue) {
                makeCalculate()
            }
            operator = Operator.valueOf(input)
        } else {
            if (input == DECIMAL_SEPARATOR) {
                when {
                    currentOperation.isEmpty() -> {
                        currentOperation += ZERO
                        currentOperation += input
                    }
                    currentOperation.contains(DECIMAL_SEPARATOR) -> {
                    }
                    else -> currentOperation += input
                }
            } else if (input == '(') {

            } else currentOperation += input
        }

        /*if (Operator.isOperator(input)) {
            operator = Operator.valueOf(input)
        } else currentOperation += input
        val current = inputParser.parse(currentOperation, input)*/
/*        if (calculate is NoValue) calculate = current
        else calculate = Operation(calculate, current)*/
        return this
    }

    private fun makeCalculate() {
        when (calculate) {
            is NoValue -> calculate = inputParser.parse(currentOperation)
            is Value -> {
                calculate = Operation(calculate, operator, inputParser.parse(currentOperation))
                operator = Operator.NoOperator
            }
            is Operation -> {
                val operation = calculate as Operation
                calculate = if (operator.getPriority() > operation.getPriority())
                    Operation(operation.first, operation.operator, Operation(operation.second, operator, inputParser.parse(currentOperation)))
                else Operation(operation, operator, inputParser.parse(currentOperation))
            }
        }
        currentOperation = ""
    }

    fun calculate(): Double {
        val res = when {
            inner != null -> {
                val innerRes = inner!!.calculate()
                if (calculate is NoValue)
                    innerRes
                else if (calculate is Operation) {
                    val operation = calculate as Operation
                    if (operator.getPriority() > operation.operator.getPriority()) {
                        Operation(operation.first, operation.operator, Operation(operation.second, operator, innerRes)).calculate()
                    } else
                    Operation(calculate, operator, innerRes).calculate()
                } else Operation(calculate, operator, innerRes).calculate()
            }
            calculate is NoValue -> inputParser.parse(currentOperation).calculate()
            operator == Operator.NoOperator || currentOperation.isEmpty() -> calculate.calculate()
            calculate is Operation && operator.getPriority() > calculate.getPriority() -> {
                val operation = calculate as Operation
                Operation(operation.first, operation.operator, Operation(operation.second, operator, inputParser.parse(currentOperation))).calculate()

            }
            else -> Operation(calculate, operator, inputParser.parse(currentOperation)).calculate()
        }
        return res
    }

    fun getResult(): String {
        val format = DecimalFormat()
        format.isGroupingUsed = false
        format.minimumFractionDigits = 0
        format.maximumFractionDigits = 340
        format.maximumIntegerDigits = 309
        format.isDecimalSeparatorAlwaysShown = false
        val symbols = format.decimalFormatSymbols
        symbols.decimalSeparator = DECIMAL_SEPARATOR
        format.decimalFormatSymbols = symbols
        return format.format(calculate())
    }

    fun getOperationString(): String = (if (calculate is NoValue) "" + operator + currentOperation else calculate.toString() + operator + currentOperation) +
            if (inner != null) ("(" + inner?.getOperationString()) else ""
}