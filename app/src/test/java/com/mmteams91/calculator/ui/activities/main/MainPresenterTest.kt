package com.mmteams91.calculator.ui.activities.main

import com.mmteams91.calculator.core.calculate.nextDigit
import com.mmteams91.calculator.core.calculate.nextOperator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by User New on 22.12.2017.
 */
class MainPresenterTest {
    private lateinit var mainPresenter: MainPresenter

    private lateinit var random: Random

    private lateinit var mainView: MainViewMock

    @Before
    fun setUp() {
        mainView = MainViewMock()
        mainPresenter = MainPresenter()
        mainPresenter.takeView(mainView)
        random = Random()
    }

    /**
     * GIVEN user input operator
     * WHEN user not input yet
     * THAN added 0 and operation in operations
     */
    @Test
    fun checkInput_inputOperator_noBefore() {
        val operator = random.nextOperator()
        mainPresenter.onClick(operator.toString())
        assertEquals("MainView operations", "0" + operator, mainView.operations)
        assertEquals("MainView result", "0", mainView.result)
    }

    /**
     * GIVEN user input decimal point
     * WHEN user not input yet
     * THAN result show 0 and point is deactivated.
     */
    @Test
    fun checkInput_inputPoint_noBefore_resultZeroWithPoint() {
        mainView.init()
        mainPresenter.onClick(".")
        assert(mainView.operations.isEmpty()) { "MainView operations" }
        assertEquals("MainView result", "0", mainView.result)

    }

    /**
     * GIVEN user input digit
     * WHEN user input point
     * THAN result show 0.+digit.
     */
    @Test
    fun checkInput_inputDigit_pointBefore_resultDecimalNumber() {
        mainView.init()
        val input = random.nextDigit()
        mainPresenter.onClick(".")
        mainPresenter.onClick(input.toString())
        assert(mainView.operations.isEmpty()) { "MainView operations" }
        assertEquals("MainView result", if (input == 0) input.toString() else "0." + input, mainView.result)
    }

    /**
     * GIVEN user input digit
     * WHEN no input
     * THAN result show digit.
     */
    @Test
    fun checkInput_inputDigit_noBefore_resultThisDigit() {
        mainView.init()
        val input = random.nextDigit()
        mainPresenter.onClick(input.toString())
        assert(mainView.operations.isEmpty()) { "MainView operations" }
        assertEquals("MainView result", input.toString(), mainView.result)
    }

    /**
     * GIVEN user input operator
     * WHEN digits
     * THAN result show digits, operations digits + operation.
     */
    @Test
    fun checkInput_inputOperator_digitsBefore_resultDigitsOperationsDigitsWithOperation() {
        mainView.init()
        val before = random.nextDouble()
        val input = random.nextOperator()
        before.toString().toCharArray().forEach { c -> mainPresenter.onClick(c.toString()) }
        mainPresenter.onClick(input.toString())
        assertEquals("MainView operations", before.toString() + input, mainView.operations)
        assertEquals("MainView result", before.toString(), mainView.result)
    }

    /**
     * GIVEN user input operator
     * WHEN digits with operator
     * THAN result show digits, operations digits + operation.
     */
    @Test
    fun checkInput_inputOperator_digitsBefore_resultDigitsOperationsDigitsWithOperation1() {
        mainView.init()
        mainPresenter.onClick("1")
        mainPresenter.onClick("1")
        mainPresenter.onClick("*")
        mainPresenter.onClick("-")
        mainPresenter.onClick("1")
        mainPresenter.onClick("*")
        assertEquals("MainView operations", "11-1*", mainView.operations)
        assertEquals("MainView result", "10", mainView.result)
    }

    /**
     * GIVEN user input point
     * WHEN digits with point
     * THAN result show digits, operations digits + point.
     */
    @Test
    fun checkInput_inputPoint_digitsWithPointBefore_resultDigitsOperationDigitsWithPoint() {
        mainView.init()
        mainPresenter.onClick("2")
        mainPresenter.onClick("1")
        mainPresenter.onClick(".")
        mainPresenter.onClick(".")
        assertEquals("MainView operations", "21.", mainView.operations)
        assertEquals("MainView result", "21", mainView.result)
    }

    /**
     * GIVEN that user entered (
     * WHEN a user no enter yet
     * THEN calculator result 0 operations , operations show left brace
     */
    @Test
    fun checkInput_leftBraceInput_noInputYet_return0inResultLeftBraceInOperations(){
        mainView.init()
        mainPresenter.onClick("(")
        assertEquals("MainView result", "0", mainView.result)
        assertEquals("MainView operations", "(", mainView.operations)
        mainPresenter.onClick("2")
        assertEquals("MainView result", "2", mainView.result)
        assertEquals("MainView operations", "(2", mainView.operations)
        mainPresenter.onClick("+")
        assertEquals("MainView result", "2", mainView.result)
        assertEquals("MainView operations", "(2+", mainView.operations)
        mainPresenter.onClick("3")
        assertEquals("MainView result", "5", mainView.result)
        assertEquals("MainView operations", "(2+3", mainView.operations)
    }

    private open class MainViewMock : IMainView {


        override fun init() {
            operations = ""
            result = "0"
        }

        var operations: String = ""

        override fun showOperations(operations: String) {
            this.operations = operations
        }

        var result: String = "0"

        override fun showResult(result: String) {
            this.result = result
        }

    }
}