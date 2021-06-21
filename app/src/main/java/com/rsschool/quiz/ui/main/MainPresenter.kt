package com.rsschool.quiz.ui.main

import com.rsschool.quiz.data.Storage

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val storage = Storage

    override fun getQuestionsCount() = storage.questions.size

    override fun startWithTheFirstQuestion() {
        view.showFirstQuestionScreen()
    }

    override fun listenOnPreviousQuestionClick() {
        view.showNextQuestionScreen()
    }

    override fun listenOnNextQuestionClick() {
        view.showPreviousQuestionScreen()
    }
}