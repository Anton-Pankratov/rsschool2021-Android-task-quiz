package com.rsschool.quiz_old.ui.pager

import androidx.fragment.app.Fragment
import com.rsschool.quiz_old.utils.OnCurrentFragmentListener

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