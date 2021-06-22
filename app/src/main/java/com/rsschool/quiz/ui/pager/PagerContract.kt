package com.rsschool.quiz.ui.pager

import androidx.fragment.app.Fragment
import com.rsschool.quiz.ui.utils.OnCurrentFragmentListener

interface PagerContract {

    interface View {
        fun setViewPager()
        fun setCurrentFragmentToListen()
        fun setChangePageAction()
        fun setPagerPresenterInActivity()
    }

    interface Presenter {
        fun createFragments(): List<Fragment>
        fun initViewPager()
        fun listenPageChangeAction()
        fun initOnCurrentFragmentListener(listener: OnCurrentFragmentListener)
        fun setCurrentFragmentInListener(questionId: Int)
        fun providePagerPresenter()
    }
}