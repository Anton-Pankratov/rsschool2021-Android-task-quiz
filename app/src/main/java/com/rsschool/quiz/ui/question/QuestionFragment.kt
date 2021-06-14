package com.rsschool.quiz.ui.question

import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.databinding.FragmentQuestionBinding
import org.koin.android.ext.android.inject

class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {

    override val viewModel: QuestionViewModel by inject()

    override fun getViewBinding() = FragmentQuestionBinding.inflate(layoutInflater)

    companion object {
        fun getInstance() = QuestionFragment()
    }
}