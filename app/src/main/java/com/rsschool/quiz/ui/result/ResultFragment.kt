package com.rsschool.quiz.ui.result

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.utils.setAlphaAnimation

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultContract.Presenter>(), ResultContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentResultBinding
        get() = FragmentResultBinding::inflate

    override val presenter: ResultContract.Presenter
        get() = ResultPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onEachButtonClicks()
    }

    override fun onResume() {
        super.onResume()
        presenter.apply {
            onSetAnimation()
            onSetResultText(
                resources.getString(
                    R.string.result_title_total_score
                )
            )
        }
    }

    override fun setAnimation() {
        binding.root.apply {
            isVisible = true
            setAlphaAnimation()
        }
    }

    override fun provideResultPresenter() = presenter

    override fun setResultText(result: String) {
        binding.totalScore.text = result.transform()
    }

    override fun getSharingTitleResource(): String =
        resources.getString(R.string.result_title_total_score)

    override fun setOnShareResultClickListener() {
        binding.buttonShare.setOnClickListener {
            presenter.listenOnShareResult()
        }
    }

    override fun setOnRepeatQuizClickListener() {
        binding.buttonRepeat.setOnClickListener {
            presenter.apply {
                resetAnswers()
                listenOnRepeatQuiz()
            }
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