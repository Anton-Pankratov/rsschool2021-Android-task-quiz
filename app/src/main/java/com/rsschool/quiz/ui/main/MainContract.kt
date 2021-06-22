package com.rsschool.quiz.ui.main

import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter
import com.rsschool.quiz.ui.result.ResultPresenter

interface MainContract {

    interface View {
        fun setNewInstanceOfPager()
        fun setOnNavigationIconClickListener()
        fun setPagerPresenter(pagerPresenter: PagerPresenter)
        fun setResultPresenter(resultPresenter: ResultPresenter)
        fun setOnChangePageListener(listener: OnChangePageListener)
        fun showNextQuestionPage()
        fun showPreviousQuestionPage()
        fun setSignalAboutAnswerSelected()
        fun setViewsByCurrentFragment()
        fun setViewsInvisibleOnResultPage()
        fun setOnClicksFromResultPage()
        fun closeMainActivity()
    }

    interface Presenter {
        fun initNewInstanceOfPager()
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
        fun listenClicksFromResultPage()
        fun exitFromQuizApp()
        fun resetAnswerList()
    }
}