package com.rsschool.quiz.ui.main

import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var onChangePageListener: OnChangePageListener? = null

    override fun listenCurrentFragment(
        pagerPresenter: PagerPresenter,
        onCurrentFragmentListener: OnCurrentFragmentListener
    ) {
        pagerPresenter.initOnCurrentFragmentListener(onCurrentFragmentListener)
    }

    override fun listenOnNextQuestionClick() {
        view.showNextQuestionPage()
    }

    override fun listenOnPreviousQuestionClick() {
        view.showPreviousQuestionPage()
    }

    override fun initOnChangePageListener(listener: OnChangePageListener) {
        onChangePageListener = listener
    }

    override fun setOnChangePageAction(action: String) {
        onChangePageListener?.onChangePage(action)
    }
}