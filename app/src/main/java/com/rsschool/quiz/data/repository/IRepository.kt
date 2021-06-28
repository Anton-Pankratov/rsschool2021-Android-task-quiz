package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.QuestionEntity

interface IRepository {

    fun getQuestionById(questionId: Int): QuestionEntity
    fun getQuestionsCount(): Int
    fun getAnswers(): List<Int>
    fun getQuestions(): List<QuestionEntity>
    fun keepUserAnswer(answer: Pair<Int, Int>)
    fun resetAnswers()
}