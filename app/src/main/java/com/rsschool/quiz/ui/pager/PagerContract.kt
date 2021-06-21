package com.rsschool.quiz.ui.pager

import androidx.fragment.app.Fragment

interface PagerContract {

    interface View {
        fun setViewPager()
        fun setCurrentFragmentToListen()
        fun setPagerPresenterInActivity()
        fun setChangePageAction()
    }

    interface Presenter {
        fun initViewPager()
        fun createFragments(): List<Fragment>
        fun initOnCurrentFragmentListener(listener: OnCurrentFragmentListener)
        fun setCurrentFragmentInListener(questionId: Int)
        fun providePagerPresenter()
        fun listenPageChangeAction()
    }
}