package com.rsschool.quiz.ui.main

import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter

class MainPresenter(private val view: MainContract.View) : BasePresenter(), MainContract.Presenter {

    private var onChangePageListener: OnChangePageListener? = null

    init { initRepository() }

    override fun initNewInstanceOfPager() {
        view.setNewInstanceOfPager()
    }

    override fun listenOnNavigationIconClick() {
        view.setOnNavigationIconClickListener()
    }

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

    override fun getAnswersList() = repository?.getAnswers()

    override fun makeViewsInvisibleOnResultPage() {
        view.setViewsInvisibleOnResultPage()
    }

    override fun listenClicksFromResultPage() {
        view.setOnClicksFromResultPage()
    }

    override fun exitFromQuizApp() {
        view.closeMainActivity()
    }

    override fun resetAnswerList() {
        repository?.resetAnswers()
    }
}