package com.rsschool.quiz.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.ui.base.BasePresenter

class ResultFragment : BaseFragment<FragmentResultBinding>(), ResultContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override val presenter = ResultPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showQuizResult()
    }

    override fun showQuizResult() {
        binding.totalScore.text =
            String.format(
                getString(R.string.result_title_total_score),
                presenter.calculateResult()
            )
    }

    override fun setOnShareClick() {
        TODO("Not yet implemented")
    }

    override fun setOnRepeatClick() {
        TODO("Not yet implemented")
    }

    override fun setOnExitClick() {
        TODO("Not yet implemented")
    }


}