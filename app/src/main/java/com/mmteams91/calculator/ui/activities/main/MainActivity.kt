package com.mmteams91.calculator.ui.activities.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.mmteams91.calculator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {
    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun init() {
        for (i in 0..number_wrapper.childCount) {
            number_wrapper.getChildAt(i)?.setOnClickListener { v ->
                presenter.onClick((v as Button).text)
            }
        }
        for (i in 0..operator_wrapper.childCount) {
            operator_wrapper.getChildAt(i)?.setOnClickListener { v ->
                presenter.onClick((v as Button).text)
            }
        }
        for (i in 0..finish_wrapper.childCount) {
            finish_wrapper.getChildAt(i)?.setOnClickListener { v ->
                presenter.onClick((v as Button).text)
            }
        }
        result.text = "0"
    }

    override fun showOperations(operations: String) {
        this.operations.text = operations
    }

    override fun showResult(result: String) {
        this.result.text = result
    }

}
