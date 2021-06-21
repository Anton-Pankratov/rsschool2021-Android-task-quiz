package com.rsschool.quiz.ui.main

import com.rsschool.quiz.data.Storage

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val storage = Storage

    override fun listenOnNextQuestionClick() {
        view.showNextQuestionScreen()
    }

    override fun listenOnPreviousQuestionClick() {
        view.showPreviousQuestionScreen()
    }
}