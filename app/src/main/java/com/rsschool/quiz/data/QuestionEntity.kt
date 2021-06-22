package com.rsschool.quiz.data

data class QuestionEntity(
    val id: Int,
    val title: String?,
    val answerVariants: List<String>?,
    val answerCorrect: Int
)