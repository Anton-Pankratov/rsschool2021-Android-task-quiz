package com.rsschool.quiz.ui.main

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.ui.base.BasePresenter
import com.rsschool.quiz.ui.question.QuestionContract
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.result.ResultContract
import com.rsschool.quiz.ui.result.ResultFragment
import com.rsschool.quiz.utils.Action
import com.rsschool.quiz.utils.QuizFragmentFactory

class MainPresenter(private val view: MainContract.View) :
    BasePresenter(), MainContract.Presenter {

    init {
        initRepository()
    }

    override fun onSetFlags() {
        view.setWindowFlags()
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
                    fragments.add(createQuestionFragment(id))
                    if (id == this) {
                        fragments.add(createResultFragment())
                    }
                }
            }
        }
        return fragments.toList()
    }

    override fun createQuestionFragment(questionId: Int) =
        QuestionFragment(questionId).also {
            it.provideQuestionPresenter()
                .setOnQuestionButtonListener(
                    createQuestionButtonsListener()
                )
        }

    override fun createResultFragment() =
        ResultFragment().also {
            it.provideResultPresenter()
                .setOnResultScreenButtonsClickListener(
                    createResultButtonsListener()
                )
        }

    override fun createQuestionButtonsListener() =
        object : QuestionContract.OnQuestionScreenButtonsListener {
            override fun onPrevious() {
                view.setAction(Action.PREVIOUS)
            }

            override fun onNext() {
                view.setAction(Action.NEXT)
            }
        }

    override fun createResultButtonsListener() =
        object : ResultContract.OnResultScreenButtonsListener {
            override fun onShareClick(shareIntent: Intent) {
                view.setAction(Action.SHARE, shareIntent)
            }

            override fun onRepeatClick() {
                view.setAction(Action.REPEAT)
            }

            override fun onExitClick() {
                view.setAction(Action.EXIT)
            }
        }
}