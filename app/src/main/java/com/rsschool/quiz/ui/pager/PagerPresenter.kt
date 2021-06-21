package com.rsschool.quiz.ui.pager

import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.result.ResultFragment

class PagerPresenter(private val view: PagerContract.View)
    : BasePresenter(), PagerContract.Presenter {

    private var _onCurrentFragmentListener: OnCurrentFragmentListener? = null
    private val onCurrentFragmentListener get() = _onCurrentFragmentListener

    override fun initOnCurrentFragmentListener(listener: OnCurrentFragmentListener) {
        _onCurrentFragmentListener = listener
    }

    override fun setCurrentFragmentInListener(questionId: Int) {
        onCurrentFragmentListener?.onCurrentFragment(questionId)
    }

    override fun initViewPager() {
        view.setViewPager()
    }

    override fun createFragments(): List<Fragment> {
        val fragments = mutableListOf<Fragment>()
        repository?.getQuestionsCount().apply {
            if (this != null) {
                for (i in 1..this) {
                    fragments.add(QuestionFragment(i))
                    if (i == this) {
                        fragments.add(ResultFragment())
                    }
                }
            }
        }
        return fragments.toList()
    }

    override fun providePagerPresenter() {
        view.setPagerPresenterInActivity()
    }

    override fun listenPageChangeAction() {
        view.setChangePageAction()
    }
}