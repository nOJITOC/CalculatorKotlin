package com.mmteams91.calculator.core.calculate

/**
 * Created by User New on 19.12.2017.
 */
abstract class Calculate {
    abstract fun calculate(): Double
    abstract fun getPriority(): Int
}
