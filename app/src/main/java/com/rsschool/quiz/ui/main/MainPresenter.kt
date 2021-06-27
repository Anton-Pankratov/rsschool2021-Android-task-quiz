package com.rsschool.quiz.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.question.QuestionContract
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.question.QuestionPresenter
import com.rsschool.quiz.ui.result.ResultFragment
import com.rsschool.quiz.ui.utils.NEXT
import com.rsschool.quiz.ui.utils.PREVIOUS
import com.rsschool.quiz.ui.utils.QuizFragmentFactory
import com.rsschool.quiz.ui.utils.provideTheme

class MainPresenter(private val view: MainContract.View) : BasePresenter(), MainContract.Presenter {

    init {
        initRepository()
    }

    override fun initFragmentFactory(fragmentManager: FragmentManager) {
        fragmentManager.fragmentFactory = QuizFragmentFactory
    }

    override fun onSetViewPager() {
        view.setFragmentsPager()
    }

    override fun onCreatePagerAdapter(activity: MainContract.View): PagerAdapter {
        return PagerAdapter(activity, formFragments())
    }

    override fun formFragments(): List<Fragment> {
        val fragments = mutableListOf<Fragment>()
        repository?.getQuestionsCount().apply {
            if (this != null) {
                for (id in 1..this) {
                    fragments.add(QuestionFragment(id).also {
                        it.providePresenter().setOnQuestionButtonListener(
                            object : QuestionContract.OnQuestionButtonListener {
                                override fun onPrevious() {
                                    view.setQuestionAction(PREVIOUS)
                                }

                                override fun onNext() {
                                    view.setQuestionAction(NEXT)
                                }
                            })
                    })
                    if (id == this) {
                        fragments.add(ResultFragment())
                    }
                }
            }
        }
        return fragments.toList()
    }

    override fun listenQuestionAction() {
       /* questionPresenter?.setOnQuestionButtonListener(
            object : QuestionContract.OnQuestionButtonListener {
                override fun onPrevious() {
                    view.setQuestionAction(PREVIOUS)
                }

                override fun onNext() {
                    view.setQuestionAction(NEXT)
                }
            })*/
    }
}