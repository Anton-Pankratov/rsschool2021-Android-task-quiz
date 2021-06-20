package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.Storage

class QuizRepository : IRepository {

    private val storage = Storage

    override fun keepAnswerOnQuestion(answerId: Int) { storage.userAnswerIds.add(answerId) }

    override fun getQuestionById(questionId: Int) = storage.questions[questionId]

    override fun getQuestionsCount() = storage.questions.size
}