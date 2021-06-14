package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.source.RemoteQuestion
import com.rsschool.quiz.data.source.ServerSourceCallerImpl

class QuestionsRepositoryImpl(private val dataSource: ServerSourceCallerImpl): QuestionsRepository {

    override fun fetchQuestions() = dataSource.fetchQuestions()
}