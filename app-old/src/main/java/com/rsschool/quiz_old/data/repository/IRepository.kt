package com.rsschool.quiz_old.data.repository

interface IRepository {

    fun getQuestionById(questionId: Int): com.rsschool.quiz_old.data.QuestionEntity
    fun getQuestionsCount(): Int
    fun getAnswers(): List<Int>
    fun getQuestions(): List<com.rsschool.quiz_old.data.QuestionEntity>
    fun keepUserAnswer(answer: Pair<Int, Int>)
    fun resetAnswers()
}