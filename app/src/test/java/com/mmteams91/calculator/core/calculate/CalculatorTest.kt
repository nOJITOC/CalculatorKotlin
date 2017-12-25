package com.mmteams91.calculator.core.calculate

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by User New on 20.12.2017.
 */
class CalculatorTest {

    private val delta = 1e-10

    private lateinit var random: Random

    @Before
    fun setUp() {
        random = Random()
    }

    /**
     * GIVEN that user entered '-'
     * WHEN a user input a string containing decimal number
     * THEN calculator return that number
     */
    @Test
    fun calculate_digitInput_minusEntered_calculatorReturnThatDoubleNumber() {
        val calculator = Calculator().append('-')
        val input = random.nextDigit()
        assertEquals(-input.toDouble(), calculator.append(input.toDigit()).calculate(), delta)
    }

//    /**
//     * GIVEN that user entered '*'
//     * WHEN a user input a string containing decimal number
//     * THEN calculator return that number
//     */
//    @Test
//    fun calculate_digitInput_operatorInput_calculatorReturnThatDoubleNumber() {
//        val calculator = Calculator().append('*')
//        val input = random.nextDigit()
//        assertEquals(input.toDouble(), calculator.append(input.toDigit()).calculate(), delta)
//    }


    /**
     * GIVEN that user input yet
     * WHEN a user input a string containing decimal number
     * THEN calculator return that number
     */
    @Test
    fun calculate_digitInput_noOtherInputs_calculatorReturnThatDoubleNumber() {
        val calculator = Calculator()
        val input = random.nextDigit()
        assertEquals(input.toDouble(), calculator.append(input.toDigit()).calculate(), delta)
    }

    /**
     * GIVEN that user didn't input yet
     * WHEN a user input a operator
     * THEN calculator return 0.0
     */
    @Test
    fun calculateInputOperator_noInputs_returnZero() {
        val calculator = Calculator()
        val input = Operator.random().toChar()
        assertEquals(0.0, calculator.append(input).calculate(), delta)
    }

    /**
     * GIVEN that user input a double number
     * WHEN a user input a operator
     * THEN calculator return that number
     */
    @Test
    fun calculate_inputOperator_inputedDoubleNumber_calculatorReturnInputedDoubleNumber() {
        val calculator = Calculator()
        val decimalNumber = random.nextDouble()
        calculator.currentOperation = decimalNumber.toString()
        val input = random.nextOperator()
        assertEquals(decimalNumber, calculator.append(input.toChar()).calculate(), delta)
    }

    /**
     * GIVEN that user input a double number
     * WHEN a user inputed a number with operator
     * THEN calculator return result of operation
     */
    @Test
    fun calculateInputNumber_inputedNumberWithOperator_returnResult() {
        var calculator = Calculator()
                .append(Random().nextDigit().toDigit())
                .append(Random().nextDigit().toDigit())
                .append(Random().nextDigit().toDigit())
                .append('.')
                .append(Random().nextDigit().toDigit())
                .append(Random().nextDigit().toDigit())
                .append(Random().nextDigit().toDigit())
        calculator.append('+')
        return
        val input = Random().nextDigit()
        val charDigit = input.toDigit()
        var numberInCalculator = calculator.calculate() + input
        assertEquals("plus", numberInCalculator, calculator.append(charDigit).calculate(), delta)
        calculator.append('-')
        numberInCalculator -= input
        assertEquals("minus", numberInCalculator, calculator.append(charDigit).calculate(), delta)
        calculator.append('*')
        numberInCalculator *= input
        assertEquals("times", numberInCalculator, calculator.append(charDigit).calculate(), delta)
        calculator.append('/')
        numberInCalculator /= input
        assertEquals("div", numberInCalculator, calculator.append(charDigit).calculate(), delta)
        System.out.println(calculator.calculate)
    }

    /**
     * GIVEN that user input an operator
     * WHEN a user no input
     * THEN calculator return 0
     */
    @Test
    fun calculate_operatorInput_noOther_returnZero() {
        val calculator = Calculator().append(random.nextOperator().toChar())
        assertEquals(0.0, calculator.calculate(), delta)
    }


    /**
     * GIVEN that user input an operator
     * WHEN a user input digits with operator
     * THEN calculator return digits
     */
    @Test
    fun calculate_operatorInput_digitsWithOperator_returnDigits() {
        val calculator = Calculator()
        val before = random.nextSimpleOperation()
        calculator.calculate = before
        calculator.append(random.nextOperator().toChar())
        calculator.append(random.nextOperator().toChar())
        assertEquals(before.calculate(), calculator.calculate(), delta)
    }

    /**
     * GIVEN that user input an digit
     * WHEN a user input digits with 2 operator
     * THEN calculator return calculate with last operator
     */
    @Test
    fun calculate_digitInput_digitsWith2Operator_returnResultOfLastOperator() {
        val calculator = Calculator()
        val before = random.nextSimpleOperation()
        calculator.calculate = before
        calculator.append(random.nextOperator().toChar())
        calculator.append('+')
        calculator.append('2')
        assertEquals(before.calculate() + 2, calculator.calculate(), delta)
    }

    /**
     * GIVEN that user entered digit
     * WHEN a user operation with '+/-' and '* or /' operation
     * THEN calculator result
     */
    @Test
    fun calculate_digitInput_operationWithTimesBefore() {
        val calculator = Calculator()
        val before = Operation(random.nextValue(), Operator.Plus, random.nextValue())
        calculator.calculate = before
        calculator.operator = Operator.Times
        val digit = random.nextDigit()
        calculator.append(digit.toDigit())
        val res = before.second.calculate() * digit + before.first.calculate()
        assertEquals(res, calculator.calculate(), delta)
    }

}
