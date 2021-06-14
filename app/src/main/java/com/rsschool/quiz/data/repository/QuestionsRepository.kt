package com.rsschool.quiz.data.repository

import com.rsschool.quiz.data.source.RemoteQuestion

interface QuestionsRepository {

    fun fetchQuestions(): List<RemoteQuestion>

    fun saveOrUpdate(questions: List<RemoteQuestion>)

}