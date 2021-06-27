package com.rsschool.quiz.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment

class ResultFragment
    : BaseFragment<FragmentResultBinding, ResultContract.Presenter>(), ResultContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override val presenter: ResultContract.Presenter
        get() = ResultPresenter()
}