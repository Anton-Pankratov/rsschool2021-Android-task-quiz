package com.rsschool.quiz.ui.question

import android.content.Context
import android.widget.RadioGroup
import androidx.annotation.StyleRes
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.data.QuestionEntity
import com.rsschool.quiz.ui.base.BaseContract
import com.rsschool.quiz.ui.utils.Action
import com.rsschool.quiz.ui.utils.Themes

interface QuestionContract {

    interface View {

        fun checkCurrentQuestionId(): Int
        fun provideQuestionPresenter(): Presenter

        /** Toolbar */
        fun setToolbarTitle(questionId: Int)
        fun setNavigationBackAction(questionId: Int)

        /** Theme */
        fun setFragmentTheme(@StyleRes theme: Int?)

        /** Question Views */
        fun setQuestionTitle(title: String?)
        fun setAnswerVariants()
        fun setOnSelectedAnswerListener()

        /** Button's View Sets */
        fun setNextQuestionAccessMode()
        fun setNextQuestionButtonText()
        fun setPreviousQuestionAccessMode()

        /** Button's Clicks */
        fun setOnNextButtonClickListener()
        fun setOnPreviousButtonClickListener()
    }

    interface Presenter : BaseContract.Presenter {

        /** Toolbar */
        fun onConfigureToolbar(questionId: Int)

        /** Theme */
        fun onSetCreatedFragmentTheme()
        fun getThemeByQuestionId(questionId: Int): Themes

        /** Question Views */
        fun formQuestionTitle(questionId: Int)
        fun onSetAnswerVariants()
        fun generateAnswersViews(radioGroup: RadioGroup, questionId: Int)
        fun createAnswerVariantView(
            context: Context, answerText: String, answerId: Int
        ): MaterialRadioButton
        fun onListenAnswerSelecting()

        /** Buttons's View Sets */
        fun configureButtons()
        fun getNextQuestionButtonMode(): Action

        /** Button's Clicks */
        fun setOnQuestionButtonListener(
            listener: OnQuestionScreenButtonsListener
        )
        fun listenOnNextClick()
        fun listenOnPreviousClick()
        fun onShowNextFragment()
        fun onShowPreviousFragment()
        fun keepSelectedAnswer(answer: Pair<Int, Int>)

        /** Question From Store */
        fun getQuestionById(questionId: Int): QuestionEntity?
        fun getQuestionSize(): Int?
    }

    interface OnQuestionScreenButtonsListener {
        fun onPrevious()
        fun onNext()
    }
}