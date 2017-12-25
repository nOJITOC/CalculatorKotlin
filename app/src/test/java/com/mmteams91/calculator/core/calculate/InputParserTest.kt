package com.mmteams91.calculator.core.calculate

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by User New on 20.12.2017.
 */
class InputParserTest {
    private lateinit var inputParser: InputParser

    private lateinit var random: Random

    private var delta = 1e-15

    @Before
    fun setUp() {
        inputParser = InputParser()
        random = Random()
    }

    @Test
    fun parse_digitInput_NoBefore_returnValuewithThisDigit() {
        val input = random.nextDigit()
        assertEquals(Value(input), inputParser.parse("", input.toDigit()))
    }

    @Test
    fun parse_operatorInput_NumberBefore_returnValueWithNumberBefore() {
        val input = random.nextOperator()
        val beforeInput = random.nextDouble()
        assertEquals(Value(beforeInput), inputParser.parse(beforeInput.toString(), input.toChar()))

    }

    @Test
    fun parse_numberInput_numberBefore_returnValueWithNewNumber() {
        val input = random.nextDigit()
        val beforeInput = random.nextDouble()
        assertEquals(Value((beforeInput.toString() + input).toDouble()).calculate(), inputParser.parse(beforeInput.toString(), input.toDigit()).calculate(), delta)

    }
    @Test
    fun parse_operatorInput_noBefore_returnValueWithZero() {
        val input = random.nextOperator()
        assertEquals(Value(0.0).calculate(), inputParser.parse("", input.toChar()).calculate(), delta)

    }
}