package com.rsschool.quiz_old.data

data class QuestionEntity(
    val id: Int,
    val title: String?,
    val answerVariants: List<String>?,
    val answerCorrect: Int
)