package com.rsschool.quiz.ui.result

import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class ResultFragment : BaseFragment<FragmentResultBinding>() {

    override val viewModel: ResultViewModel by inject()

    override fun getViewBinding(): FragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
}