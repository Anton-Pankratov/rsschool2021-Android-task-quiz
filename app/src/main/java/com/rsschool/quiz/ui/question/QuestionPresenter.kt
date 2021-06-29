package com.rsschool.quiz.ui.question

import android.content.Context
import android.widget.LinearLayout
import android.widget.RadioGroup
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.utils.*

class QuestionPresenter(private val view: QuestionContract.View) :
    BasePresenter(), QuestionContract.Presenter {

    private val questionButtonListener
        get() = _questionButtonListener

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

    override fun formQuestionTitle(questionId: Int) {
        view.setQuestionTitle(getQuestionById(questionId)?.title)
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
        )
        val padding = context.toDp(20)
        setPadding(padding, padding, padding, padding)
        text = answerText
        textSize = 20f
        id = answerId
    }

    override fun onListenAnswerSelecting() {
        view.setOnSelectedAnswerListener()
    }

    override fun configureButtons() {
        view.apply {
            setPreviousQuestionAccessMode()
            setNextQuestionAccessMode()
            setNextQuestionButtonText()
        }
    }

    override fun getNextQuestionButtonMode(): Action {
        view.checkCurrentQuestionId().let {
            return if (it == getQuestionSize()) Action.SUBMIT else Action.NEXT
        }
    }

    override fun setOnQuestionButtonListener(
        listener: QuestionContract.OnQuestionScreenButtonsListener
    ) {
        _questionButtonListener = listener
    }

    override fun listenOnNextClick() {
        view.setOnNextButtonClickListener()
    }

    override fun listenOnPreviousClick() {
        view.setOnPreviousButtonClickListener()
    }

    override fun onShowNextFragment() {
        questionButtonListener?.onNext()
    }

    override fun onShowPreviousFragment() {
        questionButtonListener?.onPrevious()
    }

    override fun keepSelectedAnswer(answer: Pair<Int, Int>) {
        answer.apply {
            repository?.keepUserAnswer(first to second)
        }
    }

    override fun getQuestionById(questionId: Int) =
        repository?.getQuestions()?.get(questionId - 1)

    override fun getQuestionSize(): Int? =
        repository?.getQuestionsCount()

    private companion object {
        var _questionButtonListener:
                QuestionContract.OnQuestionScreenButtonsListener? = null
    }
}