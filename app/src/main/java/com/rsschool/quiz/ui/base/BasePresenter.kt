package com.rsschool.quiz.ui.base

import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository

abstract class BasePresenter {

    val repository: IRepository?
        get() = _repository

    fun initRepository() {
        _repository = QuizRepository()
    }

    companion object {
        private var _repository: IRepository? = null
    }
}