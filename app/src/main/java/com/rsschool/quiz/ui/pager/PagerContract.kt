package com.rsschool.quiz.ui.pager

import com.rsschool.quiz.ui.question.QuestionFragment

interface PagerContract {

    interface View {
        fun setViewPager()
        fun setCurrentFragmentToListen()
        fun setPagerPresenterInActivity()
        fun setChangePageAction()
    }

    interface Presenter {
        fun initViewPager()
        fun createQuestionsFragments(): List<QuestionFragment>
        fun initOnCurrentFragmentListener(listener: OnCurrentFragmentListener)
        fun setCurrentFragmentInListener(questionId: Int)
        fun providePagerPresenter()
        fun listenPageChangeAction()
    }
}