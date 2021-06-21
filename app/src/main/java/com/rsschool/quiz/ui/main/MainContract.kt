package com.rsschool.quiz.ui.main

interface MainContract {

    interface View {
        fun showNextQuestionScreen()
        fun showPreviousQuestionScreen()
    }

    interface Presenter {
        fun listenOnNextQuestionClick()
        fun listenOnPreviousQuestionClick()
    }
}