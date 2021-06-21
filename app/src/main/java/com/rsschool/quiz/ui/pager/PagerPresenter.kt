package com.rsschool.quiz.ui.pager

import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.ui.question.QuestionFragment

class PagerPresenter(private val view: PagerContract.View): PagerContract.Presenter {

    private var repository: IRepository? = null

    init {
        repository = QuizRepository()
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
}