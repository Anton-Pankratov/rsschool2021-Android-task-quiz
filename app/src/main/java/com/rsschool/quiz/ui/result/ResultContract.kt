package com.rsschool.quiz.ui.result

import android.content.Intent
import android.text.SpannableString
import com.rsschool.quiz.ui.base.BaseContract

interface ResultContract {

    interface View {

        fun setAnimation()

        fun provideResultPresenter(): Presenter

        fun setResultText(result: String)
        fun getSharingTitleResource(): String?

        /** Buttons Clicks */
        fun setOnShareResultClickListener()
        fun setOnRepeatQuizClickListener()
        fun setOnExitAppClickListener()
    }

    interface Presenter : BaseContract.Presenter {

        fun onSetAnimation()

        /** Result on Screen */
        fun onSetResultText(title: String)
        fun prepareResultText(): String
        fun formCorrectAnswersRate(): String
        fun calculateResult(): Int
        fun transformText(text: String?): SpannableString

        /** Buttons Clicks*/
        fun onEachButtonClicks()
        fun setOnResultScreenButtonsClickListener(
            listener: OnResultScreenButtonsListener
        )

        /** Share Result */
        fun formShareIntent(): Intent
        fun formSharingText(): String

        /** Activity Actions */
        fun listenOnShareResult()
        fun listenOnRepeatQuiz()
        fun listenExitApp()
        fun resetAnswers()
    }

    interface OnResultScreenButtonsListener {

        fun onShareClick(shareIntent: Intent)
        fun onRepeatClick()
        fun onExitClick()
    }
}