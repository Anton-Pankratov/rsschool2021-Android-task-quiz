package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.source.QuestionEntity
import com.rsschool.quiz.data.source.ServerSourceCaller

class QuestionsRepositoryImpl(private val dataSource: ServerSourceCaller): QuestionsRepository {

    override fun fetchQuestionById(id: Int): QuestionEntity = dataSource.fetchQuestion(id)
}