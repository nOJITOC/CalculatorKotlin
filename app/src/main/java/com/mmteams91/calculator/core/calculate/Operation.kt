package com.mmteams91.calculator.core.calculate

/**
 * Created by User New on 19.12.2017.
 */
class Operation(var first: Calculate, var operator: Operator, var second: Calculate) : Calculate() {

    public operator fun plus(other: Calculate): Calculate = Operation(this, Operator.Plus, other)
    public operator fun minus(other: Calculate): Calculate = Operation(this, Operator.Minus, other)
    public operator fun times(other: Calculate): Calculate = Operation(this, Operator.Times, other)
    public operator fun div(other: Calculate): Calculate = Operation(this, Operator.Div, other)

    constructor(first: Double, operator: Operator, second: Double) : this(Value(first), operator, Value(second))


    constructor(first: Calculate, operator: Operator, second: Double) : this(first, operator, Value(second))

    override fun calculate(): Double = when (operator) {
        Operator.Plus -> first.calculate() + second.calculate()
        Operator.Minus -> first.calculate() - second.calculate()
        Operator.Div -> first.calculate() / second.calculate()
        Operator.Times -> first.calculate() * second.calculate()
        Operator.NoOperator -> first.calculate()
    }


    override fun getPriority(): Int = operator.getPriority()

    override fun toString(): String = first.toString() + operator + second

    public operator fun plus(arg: Double): Operation = Operation(this, Operator.Plus, arg)
}