package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.source.QuestionEntity

interface QuestionsRepository {

    fun fetchQuestionById(id: Int): QuestionEntity
}