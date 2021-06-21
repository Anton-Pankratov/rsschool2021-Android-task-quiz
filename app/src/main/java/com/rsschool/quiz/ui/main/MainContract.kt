package com.rsschool.quiz.ui.main

import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter

interface MainContract {

    interface View {
        fun setPagerPresenter(pagerPresenter: PagerPresenter)
        fun setViewsByCurrentFragment()
        fun setOnChangePageListener(listener: OnChangePageListener)
        fun showNextQuestionPage()
        fun showPreviousQuestionPage()
    }

    interface Presenter {
        fun listenCurrentFragment(
            pagerPresenter: PagerPresenter,
            onCurrentFragmentListener: OnCurrentFragmentListener
        )
        fun listenOnNextQuestionClick()
        fun listenOnPreviousQuestionClick()
        fun initOnChangePageListener(listener: OnChangePageListener)
        fun setOnChangePageAction(action: String)
    }
}