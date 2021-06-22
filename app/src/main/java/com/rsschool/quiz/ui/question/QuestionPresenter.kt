package com.rsschool.quiz.ui.question

import com.rsschool.quiz.utils.provideTheme
import com.rsschool.quiz.ui.base.BasePresenter

class QuestionPresenter(val view: QuestionContract.View)
    : BasePresenter(), QuestionContract.Presenter {

    override fun onSetQuestionParams(questionId: Int) {
        view.apply {
            repository?.getQuestionById(questionId)?.apply {
                title?.let { showQuestionTitle(it) }
                answerVariants?.let { showAnswerVariants(it) }
            }
        }
    }

    override fun listenSelectedQuestion(answerId: Pair<Int, Int>) {
        repository?.keepUserAnswer(answerId)
    }

    override fun passAnswerNotSelectedSignal() {
        view.setAnswerNotSelectedSignal()
    }

    override fun passAnswerSelectedSignal() {
        view.setAnswerSelectedSignal()
    }

    override fun passQuestionFragmentTheme(questionId: Int) =
        questionId.provideTheme()
}