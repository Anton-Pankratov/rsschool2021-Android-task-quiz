package com.rsschool.quiz.data.database

import androidx.room.*

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions WHERE id=:questionId")
    suspend fun getQuestionById(questionId: Int)

    @Transaction
    suspend fun updateQuestionsWithAnswersVariants(
        questions: List<Question>,
        answers: List<AnswerVariant>
    ) {
        clearQuestions()
        clearAnswersVariants()
        saveQuestions(questions)
        saveAnswerVariants(answers)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestions(questions: List<Question>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAnswerVariants(variants: List<AnswerVariant>)

    @Query("DELETE FROM questions")
    suspend fun clearQuestions()

    @Query("DELETE FROM answers")
    suspend fun clearAnswersVariants()


}