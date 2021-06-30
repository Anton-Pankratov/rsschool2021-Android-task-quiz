package com.rsschool.quiz.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.ui.base.BaseContract
import com.rsschool.quiz.ui.question.QuestionContract
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.result.ResultContract
import com.rsschool.quiz.ui.result.ResultFragment
import com.rsschool.quiz.ui.utils.Action

interface MainContract {

    interface View {

        fun setWindowFlags()
        fun setPresenter()
        fun setFragmentsPager()

        /** Question and Result Screens Actions*/
        fun setAction(
            action: Action, param: Any? = null
        )
    }

    interface Presenter : BaseContract.Presenter {

        fun onSetFlags()

        fun initFragmentFactory(fragmentManager: FragmentManager)

        /** View Pager */
        fun onSetViewPager()
        fun onCreatePagerAdapter(activity: View): PagerAdapter

        /** Form Fragments */
        fun formFragments(): List<Fragment>
        fun createQuestionFragment(questionId: Int): QuestionFragment
        fun createResultFragment(): ResultFragment

        /** Create Action Buttons Listeners */
        fun createQuestionButtonsListener():
                QuestionContract.OnQuestionScreenButtonsListener

        fun createResultButtonsListener():
                ResultContract.OnResultScreenButtonsListener

    }
}