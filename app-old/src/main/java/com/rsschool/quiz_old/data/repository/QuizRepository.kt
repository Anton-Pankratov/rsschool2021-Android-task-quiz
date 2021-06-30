package com.rsschool.quiz_old.data.repository

import android.util.Log

class QuizRepository : com.rsschool.quiz_old.data.repository.IRepository {

    private val storage = com.rsschool.quiz_old.data.Storage

    override fun getQuestionById(questionId: Int) = storage.questions[questionId - 1]

    override fun getQuestionsCount() = storage.questions.size

    override fun getAnswers() = storage.userAnswerIds

    override fun getQuestions() = storage.questions

    override fun keepUserAnswer(answer: Pair<Int, Int>) {
        storage.userAnswerIds.apply {
            answer.apply {
                set(first, second)
            }
            Log.d("ANSWERS", "Answers is $this")
        }
    }

    override fun resetAnswers() {
        storage.userAnswerIds.apply {
            forEachIndexed { index, _ ->
                set(index, -1)
            }
        }
    }
}