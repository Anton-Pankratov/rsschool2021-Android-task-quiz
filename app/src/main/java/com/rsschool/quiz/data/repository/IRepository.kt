package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.QuestionEntity

interface IRepository {

    fun keepUserAnswer(answer: Pair<Int, Int>)

    fun getQuestionById(questionId: Int): QuestionEntity

    fun getQuestionsCount(): Int

    fun getAnswers(): MutableList<Int>

    fun getQuestions(): List<QuestionEntity>
}