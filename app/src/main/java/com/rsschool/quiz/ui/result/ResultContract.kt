package com.rsschool.quiz.ui.result

import android.content.Context

interface ResultContract {

    interface View {
        fun showQuizResult()
        fun setOnShareClick()
        fun setOnRepeatClick()
        fun setOnExitClick()
        fun setSharedResultText(): String
        fun takeContextForSharing(): Context
        fun setResultPresenterInActivity()
    }

    interface Presenter {
        fun calculateResult(): Int
        fun shareResult()
        fun setOnResultButtonsClickListener(listener: OnResultPageButtonsClickListener)
        fun listenOnRepeatButtonClick()
        fun listenOnExitButtonClick()
        fun provideResultPresenter()
    }
}