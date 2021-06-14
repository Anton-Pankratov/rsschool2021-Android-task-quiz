package com.rsschool.quiz.data.database

import androidx.room.*

data class QuizQuestion(
    @Embedded val question: Question?,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val variants: List<AnswerVariant>
)

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val id: Int,
    val title: String?,
    val answerVariants: List<String>?,
    val answerCorrect: Int
)

@Entity(tableName = "answers")
data class AnswerVariant(
    @PrimaryKey val answerId: Int,
    val questionId: Int?,
    val answer: String?
)