package com.rsschool.quiz

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuizApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            this@QuizApp.apply {
                androidContext(this)
                modules(
                    listOf(
                        RoomModule(this),
                        AppModule
                    )
                )
            }
        }
    }
}