package com.rsschool.quiz.data.source

data class QuestionEntity(
    val id: Int,
    val title: String? = null,
    val answerVariants: List<String>? = null,
    val answerCorrect: Int
)