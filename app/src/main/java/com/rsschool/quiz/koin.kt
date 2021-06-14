package com.rsschool.quiz

import android.content.Context
import androidx.room.Room
import com.rsschool.quiz.data.database.AppDatabase
import com.rsschool.quiz.data.repository.QuestionsRepositoryImpl
import com.rsschool.quiz.data.source.ServerStorage
import com.rsschool.quiz.ui.main.MainViewModel
import com.rsschool.quiz.ui.question.QuestionViewModel
import com.rsschool.quiz.ui.result.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    single { ServerStorage() }
    factory { QuestionsRepositoryImpl(get()) }

    viewModel { MainViewModel() }
    viewModel { QuestionViewModel() }
    viewModel { ResultViewModel() }
}

fun RoomModule(context: Context) = module {

    single {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    single { get<AppDatabase>().questionDao() }
}