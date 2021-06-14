package com.rsschool.quiz.data.source

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ServerSourceCallerImpl : ServerSourceCaller, KoinComponent {

    private val serverStorage: ServerStorage by inject()

    override fun fetchQuestions() = serverStorage.provideQuestions()
}