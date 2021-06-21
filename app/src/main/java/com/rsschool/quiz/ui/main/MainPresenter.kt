package com.rsschool.quiz.ui.main

import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var repository: IRepository? = null

    private var onChangePageListener: OnChangePageListener? = null

    init {
        repository = QuizRepository()
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

    override fun getAnswersList() = repository?.getAnswersList()
}