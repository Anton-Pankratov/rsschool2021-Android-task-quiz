package com.rsschool.quiz.data.repository

import android.util.Log
import com.rsschool.quiz.data.Storage

class QuizRepository : IRepository {

    private val storage = Storage

    override fun keepUserAnswer(answer: Pair<Int, Int>) {
        storage.userAnswerIds.apply {
            answer.apply {
                if (getOrNull(first) != null) {
                    set(first, second)
                } else {
                    add(first, second)
                }
            }
            Log.d("ANSWERS", "Answers is $this")
        }
    }

    override fun forgotUserAnswer() {
        storage.userAnswerIds.removeLast()
    }

    override fun getQuestionById(questionId: Int) = storage.questions[questionId - 1]

    override fun getQuestionsCount() = storage.questions.size
}