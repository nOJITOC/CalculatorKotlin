package com.mmteams91.calculator.core.mvp

/**
 * Created by User New on 21.12.2017.
 */
abstract class BasePresenter<T : IView> : IPresenter<T> {
    private var view: T? = null


    override fun takeView(view: T) {
        this.view = view
        onTake()
    }

    override fun dropView() {
        onDrop()
        view = null
    }


    override fun onTake() = Unit

    override fun onDrop() = Unit

    override fun getView(): T? = view
}