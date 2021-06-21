package com.rsschool.quiz.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.result.ResultFragment

class PagerAdapter(root: Fragment, private val fragments: List<Fragment>
    ) : FragmentStateAdapter(root) {

    override fun getItemCount() =
        fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}