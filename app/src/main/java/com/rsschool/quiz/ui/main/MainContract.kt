package com.rsschool.quiz.ui.main

interface MainContract {

    interface View {
        fun showFirstQuestionScreen()
        fun showQuestionScreen()
    }

    interface Presenter {
        fun getQuestionsCount(): Int
        fun startWithTheFirstQuestion()
        fun listenOnPreviousQuestionClick()
        fun listenOnNextQuestionClick()
    }
}