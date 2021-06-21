package com.rsschool.quiz.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rsschool.quiz.ui.question.QuestionFragment

class PagerAdapter(root: Fragment, private val fragments: List<QuestionFragment>
    ) : FragmentStateAdapter(root) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}