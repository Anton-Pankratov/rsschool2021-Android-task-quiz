package com.rsschool.quiz.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.ui.main.MainActivity
import com.rsschool.quiz.utils.getStringResource

class ResultFragment : BaseFragment<FragmentResultBinding>(), ResultContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override val presenter = ResultPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.provideResultPresenter()
        showQuizResult()
        setOnClicks()
    }

    override fun showQuizResult() {
        binding.totalScore.text =
            String.format(
                getString(R.string.result_title_total_score),
                presenter.calculateResult()
            )
    }

    private fun setOnClicks() {
        setOnShareClick()
        setOnRepeatClick()
        setOnExitClick()
    }

    override fun setOnShareClick() {
        binding.buttonShare.setOnClickListener {
            presenter.shareResult()
        }
    }

    override fun setOnRepeatClick() {
        binding.buttonRepeat.setOnClickListener {
            presenter.listenOnRepeatButtonClick()
        }
    }

    override fun setOnExitClick() {
        binding.buttonExit.setOnClickListener {
            presenter.listenOnExitButtonClick()
        }
    }

    override fun takeContextForSharing() = requireContext()

    override fun setSharedResultText() =
        requireContext().getStringResource(R.string.sharing_result_score)

    override fun setResultPresenterInActivity() {
        (activity as MainActivity).setResultPresenter(presenter)
    }
}