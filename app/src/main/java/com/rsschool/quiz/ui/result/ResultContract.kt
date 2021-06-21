package com.rsschool.quiz.ui.result

interface ResultContract {

    interface View {
        fun showQuizResult()
        fun setOnShareClick()
        fun setOnRepeatClick()
        fun setOnExitClick()
    }

    interface Presenter {
        fun calculateResult(): Int
    }
}