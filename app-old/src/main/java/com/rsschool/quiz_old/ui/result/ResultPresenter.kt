package com.rsschool.quiz_old.ui.result

import android.content.Intent
import com.rsschool.quiz_old.ui.base.BasePresenter
import com.rsschool.quiz_old.utils.OnResultPageButtonsClickListener
import java.lang.StringBuilder

class ResultPresenter(val view: ResultContract.View) : BasePresenter(), ResultContract.Presenter {

    private var _onResultButtonsClickListener: OnResultPageButtonsClickListener? = null
    private val onResultButtonsClickListener get() = _onResultButtonsClickListener

    private var _questions: List<com.rsschool.quiz_old.data.QuestionEntity>? = null
    private val questions get() = _questions

    private var _answers: List<Int>? = null
    private val answers get() = _answers

    private var resultScore = 0

    override fun shareResult() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                String.format(
                    view.passSharedResultText(),
                    resultScore, formQuizResultText()
                )
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        view.takeContextForSharing().startActivity(shareIntent)
    }

    override fun listenOnRepeatButtonClick() {
        onResultButtonsClickListener?.onRepeatButtonClick()
    }

    override fun listenOnExitButtonClick() {
        onResultButtonsClickListener?.onExitButtonClick()
    }

    override fun setOnResultButtonsClickListener(
        listener: OnResultPageButtonsClickListener
    ) {
        _onResultButtonsClickListener = listener
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
            resultScore = this
            return this
        }
    }

    override fun provideResultPresenter() {
        view.setResultPresenterInActivity()
    }

    private fun formQuizResultText(): String {
        val stringBuilder = StringBuilder()
        questions?.forEachIndexed { index, question ->
            stringBuilder.append(
                question.formQuestionWithAnswerText(index)
            )
        }
        return stringBuilder.toString()
    }

    private fun com.rsschool.quiz_old.data.QuestionEntity.formQuestionWithAnswerText(index: Int): String {
        return "\n\n$id) $title" +
                "\nSelected answer: ${
                    answers?.let {
                        answerVariants?.elementAt(it[index])
                    }
                }"
    }
}