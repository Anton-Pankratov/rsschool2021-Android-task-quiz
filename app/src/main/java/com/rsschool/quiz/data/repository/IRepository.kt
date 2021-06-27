package com.rsschool.quiz.data.repository

interface IRepository {

    fun getQuestionById(questionId: Int): com.rsschool.quiz.data.QuestionEntity
    fun getQuestionsCount(): Int
    fun getAnswers(): List<Int>
    fun getQuestions(): List<com.rsschool.quiz.data.QuestionEntity>
    fun keepUserAnswer(answer: Pair<Int, Int>)
    fun resetAnswers()
}