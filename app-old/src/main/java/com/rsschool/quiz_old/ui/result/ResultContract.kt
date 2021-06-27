package com.rsschool.quiz_old.ui.result

import android.content.Context
import com.rsschool.quiz_old.utils.OnResultPageButtonsClickListener

interface ResultContract {

    interface View {
        fun showQuizResult()
        fun setOnShareClick()
        fun setOnRepeatClick()
        fun setOnExitClick()
        fun passSharedResultText(): String
        fun takeContextForSharing(): Context
        fun setResultPresenterInActivity()
    }

    interface Presenter {
        fun shareResult()
        fun listenOnRepeatButtonClick()
        fun listenOnExitButtonClick()
        fun setOnResultButtonsClickListener(
            listener: OnResultPageButtonsClickListener
        )
        fun calculateResult(): Int
        fun provideResultPresenter()
    }
}