package com.rsschool.quiz.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.ui.utils.PREPOSITION
import com.rsschool.quiz.ui.utils.RESULT_CORRECT
import com.rsschool.quiz.ui.utils.RESULT_INCORRECT
import com.rsschool.quiz.ui.utils.getStringResource

class ResultFragment
    : BaseFragment<FragmentResultBinding, ResultContract.Presenter>(), ResultContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override val presenter: ResultContract.Presenter
        get() = ResultPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.apply {
            onEachButtonClicks()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onSetResultText()
    }

    override fun provideResultPresenter() = presenter

    override fun setResultText(result: String) {
        binding.totalScore.text = result
    }

    override fun getSharingTitleResource(): String? =
        context?.getStringResource(R.string.sharing_result_score)

    override fun setOnShareResultClickListener() {
        binding.buttonShare.setOnClickListener {
            presenter.listenOnShareResult()
        }
    }

    override fun setOnRepeatQuizClickListener() {
        binding.buttonRepeat.setOnClickListener {
            presenter.listenOnRepeatQuiz()
        }
    }

    override fun setOnExitAppClickListener() {
        binding.buttonExit.setOnClickListener {
            presenter.listenExitApp()
        }
    }

    private fun String.transform(): SpannableString {
        return presenter.transformText(this)
    }
}