package com.mmteams91.calculator.core.calculate

/**
 * Created by User New on 19.12.2017.
 */
open class Value(var first: Double = 0.0, var operator: Operator = Operator.NoOperator) : Calculate() {
    public operator fun plus(other: Calculate): Calculate = Operation(this, Operator.Plus, other)
    public operator fun minus(other: Calculate): Calculate = Operation(this, Operator.Minus, other)
    public operator fun times(other: Calculate): Calculate = Operation(this, Operator.Times, other)
    public operator fun div(other: Calculate): Calculate = Operation(this, Operator.Div, other)

    constructor(int: Int) : this(int.toDouble())


    override fun calculate(): Double = first
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Value

        if (first != other.first) return false

        return true
    }

    override fun getPriority(): Int = operator.getPriority()

    override fun hashCode(): Int = first.hashCode()
    override fun toString(): String =
            if ((first.toInt() - first) == 0.0) first.toInt().toString() else String.format("%s", first)


}