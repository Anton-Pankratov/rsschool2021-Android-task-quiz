package com.rsschool.quiz.ui.pager

import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.ui.question.QuestionFragment

class PagerPresenter(private val view: PagerContract.View): PagerContract.Presenter {

    private var _repository: QuizRepository? = null
    private val repository get() = _repository

    private var _onCurrentFragmentListener: OnCurrentFragmentListener? = null
    private val onCurrentFragmentListener get()= _onCurrentFragmentListener

    init {
        _repository = QuizRepository()
    }

    override fun initOnCurrentFragmentListener(listener: OnCurrentFragmentListener) {
        _onCurrentFragmentListener = listener
    }

    override fun setCurrentFragmentInListener(questionId: Int) {
        onCurrentFragmentListener?.onCurrentFragment(questionId)
    }

    override fun initViewPager() {
        view.setViewPager()
    }

    override fun createQuestionsFragments(): List<QuestionFragment> {
        val fragments = mutableListOf<QuestionFragment>()
        repository?.getQuestionsCount().apply {
            if (this != null) {
                for (i in 1..this) {
                    fragments.add(QuestionFragment(i))
                }
            }
        }
        return fragments.toList()
    }

    override fun providePagerPresenter() {
        view.setPagerPresenterInActivity()
    }

    override fun listenPageChangeAction() {
        TODO("Not yet implemented")
    }
}