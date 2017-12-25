package com.mmteams91.calculator.core.calculate

/**
 * Created by User New on 20.12.2017.
 */
class NoValue : Calculate() {
    override fun calculate(): Double = 0.0
    override fun getPriority(): Int = 0
}