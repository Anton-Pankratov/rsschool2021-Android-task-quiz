package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.QuestionEntity

interface IRepository {

    fun keepUserAnswer(answer: Pair<Int, Int>)

    fun forgotUserAnswer()

    fun getQuestionById(questionId: Int): QuestionEntity

    fun getQuestionsCount(): Int
}