package com.rsschool.quiz.ui.question

import android.content.Context
import android.content.res.ColorStateList
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.data.QuestionEntity
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.utils.NEXT
import com.rsschool.quiz.ui.utils.SUBMIT
import com.rsschool.quiz.ui.utils.provideTheme
import com.rsschool.quiz.ui.utils.toDp

class QuestionPresenter(private val view: QuestionContract.View) :
    BasePresenter(), QuestionContract.Presenter {

    private var _currentQuestion: QuestionEntity? = null
    private val currentQuestion get() = _currentQuestion

    override fun onConfigureToolbar(questionId: Int) {
        view.apply {
            setToolbarTitle(questionId)
            setNavigationBackAction(questionId)
        }
    }

    override fun onSetCreatedFragmentTheme(questionId: Int) {
        view.setFragmentTheme(getThemeByQuestionId(questionId))
    }

    override fun getThemeByQuestionId(questionId: Int) =
        questionId.provideTheme()

    override fun formQuestionTitle() {
        view.setQuestionTitle(currentQuestion?.title)
    }

    override fun onSetAnswerVariants() {
        view.setAnswerVariants()
    }

    override fun generateAnswersViews(
        radioGroup: RadioGroup,
        questionId: Int
    ) {
        radioGroup.apply {
            getQuestionById(questionId)
                ?.answerVariants
                ?.forEachIndexed { index, variant ->
                    addView(
                        createAnswerVariantView(
                            context, variant, index + 1
                        )
                    )
                }
        }
    }

    override fun createAnswerVariantView(
        context: Context, answerText: String, answerId: Int
    ) = MaterialRadioButton(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, context.toDp(20), 0, 0)
        }
        setPadding(context.toDp(10), 0, 0, 0)
        text = answerText
        textSize = 16f
        id = answerId
    }

    override fun configureButtons() {
        view.apply {
            setPreviousQuestionAccessMode()
            setNextQuestionAccessMode()
            setNextQuestionButtonText()
        }
    }

    override fun getNextQuestionButtonMode(): String {
        view.checkCurrentQuestionId().let {
            return if (it == getQuestionSize()) SUBMIT else NEXT
        }
    }

    override fun getQuestionById(questionId: Int) =
        repository?.getQuestions()?.get(questionId - 1)
            .also { _currentQuestion = it }

    override fun getQuestionSize(): Int? =
        repository?.getQuestionsCount()

}