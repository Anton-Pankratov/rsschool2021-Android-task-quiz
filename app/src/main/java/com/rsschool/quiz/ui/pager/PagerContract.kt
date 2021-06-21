package com.rsschool.quiz.ui.pager

import com.rsschool.quiz.ui.question.QuestionFragment

interface PagerContract {

    interface View {
        fun setViewPager()
    }

    interface Presenter {
        fun initViewPager()
        fun createQuestionsFragments(): List<QuestionFragment>
    }
}