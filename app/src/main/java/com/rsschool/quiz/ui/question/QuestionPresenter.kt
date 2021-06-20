package com.rsschool.quiz.ui.question

import android.util.Log
import com.rsschool.quiz.data.Storage
import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository

class QuestionPresenter(val view: QuestionContract.View) : QuestionContract.Presenter {

    private var _repository: IRepository? = null
    private val repository get() = _repository

    init {
        _repository = QuizRepository()
    }

    override fun onCreateQuestionFragment(questionId: Int) {
        view.apply {
            repository?.getQuestionById(questionId)?.apply {
                title?.let { showQuestionTitle(it) }
                answerVariants?.let { showAnswerVariants(it) }
            }
        }
    }

    val storage = Storage

    override fun listenSelectedQuestion(answerId: Int) {
        repository?.keepAnswerOnQuestion(answerId)
        Log.d("STORAGE", "Storage keep question with id ${storage.userAnswerIds}")
    }
}