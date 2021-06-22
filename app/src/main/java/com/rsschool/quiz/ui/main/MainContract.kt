package com.rsschool.quiz.ui.main

import androidx.annotation.ColorRes
import com.rsschool.quiz.ui.utils.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter
import com.rsschool.quiz.ui.result.ResultPresenter
import com.rsschool.quiz.ui.utils.OnChangePageListener

interface MainContract {

    interface View {
        fun setOnNavigationIconClickListener()
        fun showNextQuestionPage()
        fun showPreviousQuestionPage()
        fun setViewsByCurrentFragment()
        fun setSignalAboutAnswerNotSelected()
        fun setSignalAboutAnswerSelected()
        fun setSignalAboutChangeFragmentTheme(@ColorRes theme: Int)
        fun setOnClicksFromResultPage()
        fun setViewsInvisibleOnResultPage()
        fun setOnChangePageListener(listener: OnChangePageListener)
        fun setPagerPresenter(pagerPresenter: PagerPresenter)
        fun setResultPresenter(resultPresenter: ResultPresenter)
        fun setNewInstanceOfPager()
        fun closeMainActivity()
    }

    interface Presenter {
        fun listenOnNavigationIconClick()
        fun listenOnNextQuestionClick()
        fun listenOnPreviousQuestionClick()
        fun makeViewsInvisibleOnResultPage()
        fun setOnChangePageAction(action: String)
        fun listenCurrentFragment(
            pagerPresenter: PagerPresenter,
            onCurrentFragmentListener: OnCurrentFragmentListener
        )
        fun initOnChangePageListener(listener: OnChangePageListener)
        fun listenClicksFromResultPage()
        fun getAnswersList(): List<Int>?
        fun resetAnswerList()
        fun initNewInstanceOfPager()
        fun exitFromQuizApp()
    }
}