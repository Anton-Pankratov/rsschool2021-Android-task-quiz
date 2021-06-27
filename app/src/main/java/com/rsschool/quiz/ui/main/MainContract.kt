package com.rsschool.quiz.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.ui.base.BaseContract

interface MainContract {

    interface View {
        fun setPresenter()
        fun setFragmentsPager()
    }

    interface Presenter : BaseContract.Presenter {
        fun initFragmentFactory(fragmentManager: FragmentManager)
        fun onSetViewPager()
        fun onCreatePagerAdapter(activity: View): PagerAdapter
        fun formFragments(): List<Fragment>
    }
}