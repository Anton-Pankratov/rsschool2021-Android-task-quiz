package com.rsschool.quiz

import android.app.Application
import org.koin.core.context.startKoin

class QuizApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules()
        }
    }
}