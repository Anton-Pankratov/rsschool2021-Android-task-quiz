package com.rsschool.quiz

import com.rsschool.quiz.data.source.ServerStorage
import org.koin.dsl.module

val appModule = module {

    single { ServerStorage() }
}