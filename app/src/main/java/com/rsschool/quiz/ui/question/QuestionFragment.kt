package com.rsschool.quiz.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.databinding.FragmentQuestionBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.ui.main.MainActivity

class QuestionFragment(var questionId: Int = 0) : BaseFragment<FragmentQuestionBinding>(),
    QuestionContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionBinding
        get() = FragmentQuestionBinding::inflate

    override val presenter = QuestionPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenUserSelectedQuestion()
        presenter.onSetQuestionParams(questionId = questionId)
    }

    override fun showQuestionTitle(title: String) {
        binding.titleQuestionTv.text = title
    }

    override fun showAnswerVariants(variants: List<String>) {
        binding.questionsRv.apply {
            var btnId = 0
            variants.forEach {
                addView(MaterialRadioButton(requireContext())
                    .apply {
                        id = btnId++
                        text = it
                    })
            }
        }
    }

    override fun setAnswerSelectedSignal() {
        (activity as MainActivity).setSignalAboutAnswerSelected()
    }

    private fun listenUserSelectedQuestion() {
        binding.questionsRv.setOnCheckedChangeListener { _, checkedId ->
            presenter.apply {
                listenSelectedQuestion((questionId - 1) to checkedId)
                passAnswerSelectedSignal()
            }
        }
    }
}