package com.rsschool.quiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Question::class, AnswerVariant::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun questionDao(): QuestionDao
}