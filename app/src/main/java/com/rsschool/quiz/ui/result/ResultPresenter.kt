package com.rsschool.quiz.ui.result

import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import com.rsschool.quiz.data.QuestionEntity
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.utils.PREPOSITION
import com.rsschool.quiz.ui.utils.RESULT_CORRECT
import com.rsschool.quiz.ui.utils.RESULT_INCORRECT

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

    private var correctAnswersRate = ""

    override fun onSetResultText() {
        view.setResultText(prepareResultText())
    }

    override fun prepareResultText(): String {
        return "${calculateResult()} $PREPOSITION ${questions?.size}\n\n" +
                formCorrectAnswersRate()
    }

    override fun formCorrectAnswersRate() = correctAnswersRate

    override fun calculateResult(): Int {
        _questions = repository?.getQuestions()
        _answers = repository?.getAnswers()

        questions?.let {
            answers?.compareWithCorrects(it).apply {
                _resultScore = this ?: 0
            }
        }
        return resultScore
    }

    override fun transformText(text: String?): SpannableString {
        text.apply {
            return SpannableString(this).apply {
                PREPOSITION.let { of ->
                    setSpan(
                        RelativeSizeSpan(0.7f),
                        indexOf(of),
                        indexOf(of) + of.length,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
            }
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

    private fun List<Int>.compareWithCorrects(questions: List<QuestionEntity>): Int? {
        val starRate = StringBuilder()
        var result = 0
        forEachIndexed { index, answer ->
            if (answer != questions[index].answerCorrect) {
                starRate.append(RESULT_INCORRECT)
            } else {
                starRate.append(RESULT_CORRECT)
                result++
            }
        }
        return result
    }

    private companion object {
        var _questions: List<QuestionEntity>? = null
        var _answers: List<Int>? = null
        var _resultScore = 0

        var _onResultButtonsListener:
                ResultContract.OnResultScreenButtonsListener? = null
    }
}