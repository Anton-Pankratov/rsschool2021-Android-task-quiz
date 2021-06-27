package com.rsschool.quiz_old.ui.question

interface QuestionContract {

    interface View {
        fun showQuestionTitle(title: String)
        fun showAnswerVariants(variants: List<String>)
        fun setAnswerNotSelectedSignal()
        fun setAnswerSelectedSignal()
        fun setQuestionFragmentTheme()
    }

    interface Presenter {
        fun onSetQuestionParams(questionId: Int)
        fun listenSelectedQuestion(answerId: Pair<Int, Int>)
        fun passAnswerNotSelectedSignal()
        fun passAnswerSelectedSignal()
        fun passQuestionFragmentTheme(questionId: Int): Int
    }
}