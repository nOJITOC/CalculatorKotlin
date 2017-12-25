package com.mmteams91.calculator.core.mvp

/**
 * Created by User New on 21.12.2017.
 */
interface IPresenter<T : IView> {
    fun takeView(view: T)
    fun dropView()
    fun onTake()
    fun onDrop()
    fun getView(): T?
}