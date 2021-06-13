package com.rsschool.quiz.ui

import com.rsschool.quiz.base.BaseFragment
import com.rsschool.quiz.databinding.FragmentQuestionBinding

class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {
    override fun getViewBinding() = FragmentQuestionBinding.inflate(layoutInflater)



    companion object {
        fun getInstance() = QuestionFragment()
    }
}