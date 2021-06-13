package com.rsschool.quiz.data.source

interface ServerSourceCaller {

    fun fetchQuestion(id: Int): QuestionEntity
}