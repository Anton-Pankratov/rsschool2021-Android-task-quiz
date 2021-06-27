package com.rsschool.quiz_old.ui.question

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import com.rsschool.quiz.databinding.FragmentQuestionBinding
import com.rsschool.quiz_old.ui.base.BaseFragment
import com.rsschool.quiz_old.ui.main.MainActivity
import com.rsschool.quiz_old.utils.toDp

class QuestionFragment(private var questionId: Int = 0) : BaseFragment<FragmentQuestionBinding>(),
    QuestionContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentQuestionBinding
        get() = FragmentQuestionBinding::inflate

    override val presenter = QuestionPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUserAnswerSelect()
        presenter.apply {
            questionId.also {
                onSetQuestionParams(
                    questionId = it
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkUserAnswerNotSelect()
        setQuestionFragmentTheme()
    }

    override fun showQuestionTitle(title: String) {
        binding.titleQuestionTv.text = title
    }

    override fun showAnswerVariants(variants: List<String>) {
        binding.questionsRg.apply {
            var btnId = 0
            variants.forEach {
                addView(AppCompatRadioButton(requireContext())
                    .apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0,context.toDp(20),0, 0)
                        }
                        buttonTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    context,
                                    presenter.passQuestionFragmentTheme(questionId)
                                )
                            )
                        setPadding(context.toDp(10), 0, 0, 0)
                        text = it
                        textSize = 16f
                        id = btnId++
                    })
            }
        }
    }

    override fun setAnswerNotSelectedSignal() {
        (activity as MainActivity).setSignalAboutAnswerNotSelected()
    }

    override fun setAnswerSelectedSignal() {
        (activity as MainActivity).setSignalAboutAnswerSelected()
    }

    override fun setQuestionFragmentTheme() {
        presenter.passQuestionFragmentTheme(questionId).also {
            (activity as MainActivity).setSignalAboutChangeFragmentTheme(it)

        }
    }

    private fun checkUserAnswerNotSelect() {
        if (binding.questionsRv.checkedRadioButtonId == -1) {
            presenter.passAnswerNotSelectedSignal()
        }
    }

    private fun checkUserAnswerSelect() {
        binding.questionsRv.setOnCheckedChangeListener { _, checkedId ->
            presenter.apply {
                listenSelectedQuestion((questionId - 1) to checkedId)
                passAnswerSelectedSignal()
            }
        }
    }
}