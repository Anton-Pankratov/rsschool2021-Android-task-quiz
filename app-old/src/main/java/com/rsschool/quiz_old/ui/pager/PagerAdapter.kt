package com.rsschool.quiz_old.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(root: Fragment, private val fragments: List<Fragment>)
    : FragmentStateAdapter(root) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}