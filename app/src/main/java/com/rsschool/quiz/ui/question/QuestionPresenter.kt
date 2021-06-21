package com.rsschool.quiz.ui.question

import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository

class QuestionPresenter(val view: QuestionContract.View) : QuestionContract.Presenter {

    private var _repository: IRepository? = null
    private val repository get() = _repository

    init {
        _repository = QuizRepository()
    }

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

    override fun passAnswerSelectedSignal() {
        view.setAnswerSelectedSignal()
    }
}