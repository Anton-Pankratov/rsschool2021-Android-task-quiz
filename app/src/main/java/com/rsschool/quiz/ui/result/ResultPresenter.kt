package com.rsschool.quiz.ui.result

import android.content.Intent
import com.rsschool.quiz.ui.base.BasePresenter

class ResultPresenter(val view: ResultContract.View) : BasePresenter(), ResultContract.Presenter {

    private var _onResultButtonsClickListener: OnResultPageButtonsClickListener? = null
    private val onResultButtonsClickListener get() = _onResultButtonsClickListener

    private var resultScore = 0

    override fun calculateResult(): Int {
        val questions = repository?.getQuestions()
        val answers = repository?.getAnswers()
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

    override fun shareResult() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                String.format(view.setSharedResultText(), resultScore))
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        view.takeContextForSharing().startActivity(shareIntent)
    }

    override fun setOnResultButtonsClickListener(listener: OnResultPageButtonsClickListener) {
        _onResultButtonsClickListener = listener
    }

    override fun listenOnRepeatButtonClick() {
        onResultButtonsClickListener?.onRepeatButtonClick()
    }

    override fun listenOnExitButtonClick() {
        onResultButtonsClickListener?.onExitButtonClick()
    }

    override fun provideResultPresenter() {
        view.setResultPresenterInActivity()
    }
}