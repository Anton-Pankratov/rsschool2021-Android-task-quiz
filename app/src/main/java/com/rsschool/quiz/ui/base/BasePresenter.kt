package com.rsschool.quiz.ui.base

abstract class BasePresenter {

    val repository: com.rsschool.quiz.data.repository.IRepository?
        get() = _repository

    fun initRepository() {
        _repository = com.rsschool.quiz.data.repository.QuizRepository()
    }

    companion object {
        private var _repository: com.rsschool.quiz.data.repository.IRepository? = null
    }
}