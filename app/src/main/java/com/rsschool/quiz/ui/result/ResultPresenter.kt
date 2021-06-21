package com.rsschool.quiz.ui.result

import com.rsschool.quiz.ui.base.BasePresenter

class ResultPresenter(val view: ResultContract.View) : BasePresenter(), ResultContract.Presenter {



    override fun calculateResult(): Int {
        val questions = repository?.getQuestions()
        val answers = repository?.getAnswers()
        var result = 0

        answers?.forEachIndexed { index, answer ->
            if (answer == questions?.get(index)?.answerCorrect) {
                result += 20
            }
        }
        return result
    }
}