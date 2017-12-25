package com.mmteams91.calculator.ui.activities.main

import com.mmteams91.calculator.core.mvp.IView

/**
 * Created by User New on 21.12.2017.
 */
interface IMainView : IView {
    fun init()
    fun showOperations(operations: String)
    fun showResult(result: String)
}