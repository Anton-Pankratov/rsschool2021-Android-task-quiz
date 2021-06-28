package com.rsschool.quiz.ui.result

import android.content.Intent
import com.rsschool.quiz.data.QuestionEntity
import com.rsschool.quiz.ui.base.BasePresenter
import java.lang.StringBuilder

class ResultPresenter(private val view: ResultContract.View) :
    BasePresenter(), ResultContract.Presenter {

    private val onResultButtonsListener
        get() = _onResultButtonsListener

    private val questions
        get() = _questions

    private val answers
        get() = _answers

    private val resultScore
        get() = _resultScore

    override fun onSetResultText() {
        view.setResultText(calculateResult())
    }

    override fun calculateResult(): Int {
        _questions = repository?.getQuestions()
        _answers = repository?.getAnswers()
        var result = 0

        answers?.forEachIndexed { index, answer ->
            if (answer == questions?.get(index)?.answerCorrect) {
                result += 20
            }
        }
        result.apply {
            _resultScore = this
            return this
        }
    }

    override fun onEachButtonClicks() {
        view.apply {
            setOnShareResultClickListener()
            setOnRepeatQuizClickListener()
            setOnExitAppClickListener()
        }
    }

    override fun setOnResultScreenButtonsClickListener(
        listener: ResultContract.OnResultScreenButtonsListener
    ) {
        _onResultButtonsListener = listener
    }

    override fun formShareIntent(): Intent {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                view.getSharingTitleResource()?.let { title ->
                    String.format(
                        title, resultScore, formSharingText()
                    )
                }
            )
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    override fun formSharingText(): String {
        val stringBuilder = StringBuilder()
        questions?.forEachIndexed { index, question ->
            stringBuilder.append(
                question.formQuestionWithAnswerText(index)
            )
        }
        return stringBuilder.toString()
    }

    override fun listenOnShareResult() {
        onResultButtonsListener?.onShareClick(
            formShareIntent()
        )
    }

    override fun listenOnRepeatQuiz() {
        onResultButtonsListener?.onRepeatClick()
    }

    override fun listenExitApp() {
        onResultButtonsListener?.onExitClick()
    }

    private fun QuestionEntity.formQuestionWithAnswerText(index: Int): String {
        return "\n\n$id) $title" +
                "\nSelected answer: ${
                    answers?.let {
                        answerVariants?.elementAt(it[index])
                    }
                }"
    }

    private companion object {
        var _questions: List<QuestionEntity>? = null
        var _answers: List<Int>? = null
        var _resultScore = 0

        var _onResultButtonsListener:
                ResultContract.OnResultScreenButtonsListener? = null
    }
}