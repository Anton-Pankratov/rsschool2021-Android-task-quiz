package com.rsschool.quiz_old.ui.base

abstract class BasePresenter {

    val repository: com.rsschool.quiz_old.data.repository.IRepository?
        get() = _repository

    fun initRepository() {
        _repository = com.rsschool.quiz_old.data.repository.QuizRepository()
    }

    companion object {
        private var _repository: com.rsschool.quiz_old.data.repository.IRepository? = null
    }
}