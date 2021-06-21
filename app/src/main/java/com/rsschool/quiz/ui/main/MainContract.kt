package com.rsschool.quiz.ui.main

import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter

interface MainContract {

    interface View {
        fun setOnNavigationIconClickListener()
        fun setPagerPresenter(pagerPresenter: PagerPresenter)
        fun setOnChangePageListener(listener: OnChangePageListener)
        fun showNextQuestionPage()
        fun showPreviousQuestionPage()
        fun setSignalAboutAnswerSelected()
        fun setViewsByCurrentFragment()
        fun setViewsInvisibleOnResultPage()
    }

    interface Presenter {
        fun listenOnNavigationIconClick()
        fun listenCurrentFragment(
            pagerPresenter: PagerPresenter,
            onCurrentFragmentListener: OnCurrentFragmentListener
        )
        fun listenOnNextQuestionClick()
        fun listenOnPreviousQuestionClick()
        fun initOnChangePageListener(listener: OnChangePageListener)
        fun setOnChangePageAction(action: String)
        fun getAnswersList(): MutableList<Int>?
        fun makeViewsInvisibleOnResultPage()
    }
}