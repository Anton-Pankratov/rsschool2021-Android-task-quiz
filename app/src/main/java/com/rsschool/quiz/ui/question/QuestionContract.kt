package com.rsschool.quiz.ui.question

import android.content.Context
import android.widget.RadioGroup
import androidx.annotation.StyleRes
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.data.QuestionEntity
import com.rsschool.quiz.ui.base.BaseContract

interface QuestionContract {

    interface View {
        fun checkCurrentQuestionId(): Int
        fun setToolbarTitle(questionId: Int)
        fun setNavigationBackAction(questionId: Int)
        fun setFragmentTheme(@StyleRes theme: Int)
        fun setQuestionTitle(title: String?)
        fun setAnswerVariants()
        fun setPreviousQuestionAccessMode()
        fun setNextQuestionAccessMode()
        fun setNextQuestionButtonText()
    }

    interface Presenter : BaseContract.Presenter {
        fun onConfigureToolbar(questionId: Int)
        fun onSetCreatedFragmentTheme(questionId: Int)
        fun getThemeByQuestionId(questionId: Int): Int
        fun formQuestionTitle()
        fun onSetAnswerVariants()
        fun generateAnswersViews(radioGroup: RadioGroup, questionId: Int)
        fun createAnswerVariantView(
            context: Context, answerText: String, answerId: Int
        ): MaterialRadioButton
        fun configureButtons()
        fun getNextQuestionButtonMode(): String
        fun getQuestionById(questionId: Int): QuestionEntity?
        fun getQuestionSize(): Int?
    }
}