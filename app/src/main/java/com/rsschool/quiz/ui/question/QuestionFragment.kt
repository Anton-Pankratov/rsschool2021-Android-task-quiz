package com.rsschool.quiz.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.rsschool.quiz.databinding.FragmentQuestionBinding

class QuestionFragment(var questionId: Int = 0) : Fragment(), QuestionContract.View {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding

    private val presenter = QuestionPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(
            inflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenUserSelectedQuestion()
        presenter.onSetQuestionParams(questionId = questionId)
    }

    override fun showQuestionTitle(title: String) {
        binding?.titleQuestionTv?.text = title
    }

    override fun showAnswerVariants(variants: List<String>) {
        binding?.questionsRv.apply {
            var btnId = 0
            variants.forEach {
                this?.addView(MaterialRadioButton(requireContext()).apply {
                    id = btnId++
                    text = it
                })
            }
        }
    }

    private fun listenUserSelectedQuestion() {
        binding?.questionsRv?.setOnCheckedChangeListener { _, checkedId ->
            presenter.listenSelectedQuestion((questionId - 1) to checkedId)
        }
    }

    private companion object {
        const val ANSWER_ID_KEY = "answerIdKey"
    }
}