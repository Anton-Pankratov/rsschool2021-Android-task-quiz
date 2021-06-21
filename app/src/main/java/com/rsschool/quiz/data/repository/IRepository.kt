package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.QuestionEntity

interface IRepository {

    fun getQuestionById(questionId: Int): QuestionEntity

    fun getQuestionsCount(): Int
}