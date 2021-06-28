package com.rsschool.quiz.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    mainActivity: MainContract.View,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(mainActivity as MainActivity) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) =
        fragments[position]
}