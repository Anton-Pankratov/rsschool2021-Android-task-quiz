package com.rsschool.quiz.data.source

interface ServerSourceCaller {

    fun fetchQuestions(): List<RemoteQuestion>?
}