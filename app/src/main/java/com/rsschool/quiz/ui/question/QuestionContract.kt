package com.rsschool.quiz.ui.question

interface QuestionContract {

    interface View {
        fun showQuestionTitle(title: String)
        fun showAnswerVariants(variants: List<String>)
    }

    interface Presenter {
        fun onSetQuestionParams(questionId: Int)
        fun listenSelectedQuestion(answerId: Pair<Int, Int>)
    }
}