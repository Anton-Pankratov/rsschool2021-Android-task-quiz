package com.rsschool.quiz_old.ui.main

import com.rsschool.quiz_old.ui.base.BasePresenter
import com.rsschool.quiz_old.utils.OnCurrentFragmentListener
import com.rsschool.quiz_old.ui.pager.PagerPresenter
import com.rsschool.quiz_old.utils.OnChangePageListener

class MainPresenter(private val view: MainContract.View) : BasePresenter(), MainContract.Presenter {

    private var onChangePageListener: OnChangePageListener? = null

    init { initRepository() }

    override fun listenOnNavigationIconClick() {
        view.setOnNavigationIconClickListener()
    }

    override fun listenOnNextQuestionClick() {
        view.showNextQuestionPage()
    }

    override fun listenOnPreviousQuestionClick() {
        view.showPreviousQuestionPage()
    }

    override fun makeViewsInvisibleOnResultPage() {
        view.setViewsInvisibleOnResultPage()
    }

    override fun setOnChangePageAction(action: String) {
        onChangePageListener?.onChangePage(action)
    }

    override fun listenCurrentFragment(
        pagerPresenter: PagerPresenter,
        onCurrentFragmentListener: OnCurrentFragmentListener
    ) {
        pagerPresenter.initOnCurrentFragmentListener(onCurrentFragmentListener)
    }

    override fun initOnChangePageListener(listener: OnChangePageListener) {
        onChangePageListener = listener
    }

    override fun listenClicksFromResultPage() {
        view.setOnClicksFromResultPage()
    }

    override fun getAnswersList() = repository?.getAnswers()?.toList()

    override fun resetAnswerList() {
        repository?.resetAnswers()
    }

    override fun initNewInstanceOfPager() {
        view.setNewInstanceOfPager()
    }

    override fun exitFromQuizApp() {
        view.closeMainActivity()
    }
}