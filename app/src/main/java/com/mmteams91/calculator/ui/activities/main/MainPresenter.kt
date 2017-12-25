package com.mmteams91.calculator.ui.activities.main

import android.support.annotation.VisibleForTesting
import com.mmteams91.calculator.core.calculate.Calculator
import com.mmteams91.calculator.core.mvp.BasePresenter

/**
 * Created by User New on 21.12.2017.
 */
class MainPresenter(var calculator: Calculator = Calculator()) : BasePresenter<IMainView>() {
    val clear = 'C'
    val finish = '='
    override fun onTake() {
        getView()?.init()
    }

    fun onClick(title: CharSequence) = checkInput(title[0])

    @VisibleForTesting
    private fun checkInput(char: Char) = when (char) {
        clear -> clear()
        finish -> {
            val result = calculator.getResult()
            calculator = Calculator()
            calculator.currentOperation = if (result == "0") "" else result
            showCalculate()
        }
        else -> {
            calculator.append(char)
            showCalculate()
        }
    }

    private fun clear() {
        calculator = Calculator()
        showCalculate()
    }

    private fun showCalculate() {
        getView()?.showOperations(calculator.getOperationString())
        getView()?.showResult(calculator.getResult())
    }


}
