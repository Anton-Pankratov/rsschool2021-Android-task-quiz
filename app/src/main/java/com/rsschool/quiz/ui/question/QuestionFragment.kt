package com.rsschool.quiz.ui.question

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentQuestionBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.utils.Action

class QuestionFragment(private val questionId: Int = 0) :
    BaseFragment<FragmentQuestionBinding, QuestionContract.Presenter>(),
    QuestionContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentQuestionBinding
        get() = FragmentQuestionBinding::inflate

    override val presenter: QuestionContract.Presenter
        get() = QuestionPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter.onSetCreatedFragmentTheme()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.apply {
            questionId.apply {
                onConfigureToolbar(this)
                formQuestionTitle(this)
                onSetAnswerVariants()
                onListenAnswerSelecting()
                configureButtons()
                listenOnNextClick()
                listenOnPreviousClick()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor = requireActivity().getThemeColor(android.R.attr.statusBarColor)
    }

    override fun checkCurrentQuestionId() = questionId

    override fun provideQuestionPresenter() = presenter

    override fun setToolbarTitle(questionId: Int) {
        binding.toolbar.apply {
            title = String.format(
                resources.getString(
                    R.string.toolbar_title
                ), questionId
            )
        }
    }

    override fun setNavigationBackAction(questionId: Int) {
        binding.toolbar.apply {
            (activity as AppCompatActivity).setSupportActionBar(this)
            if (questionId != 1) {
                setNavigationOnClickListener {
                    presenter.onShowPreviousFragment()
                }
            } else {
                navigationIcon = null
            }
        }
    }

    override fun setFragmentTheme(@StyleRes theme: Int?) {
        theme?.let { activity?.setTheme(it) }
    }

    fun Context.getThemeColor(@AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute (attrRes, typedValue, true)
        return typedValue.data
    }

    override fun setQuestionTitle(title: String?) {
        binding.questionTitleTv.text = title
    }

    override fun setAnswerVariants() {
        binding.answersVariantsRg.apply {
            presenter.generateAnswersViews(this, questionId)
        }
    }

    override fun setOnSelectedAnswerListener() {
        binding.apply {
            answersVariantsRg.setOnCheckedChangeListener { group, checkedId ->
                nextBtn.isEnabled = true
                presenter.keepSelectedAnswer(questionId - 1 to checkedId - 1)
            }
        }
    }


    override fun setNextQuestionAccessMode() {
        binding.apply {
            nextBtn.setAccessModeBy {
                answersVariantsRg.checkedRadioButtonId != -1
            }
        }
    }

    override fun setNextQuestionButtonText() {
        binding.nextBtn.text =
            when (presenter.getNextQuestionButtonMode()) {
                Action.NEXT -> requireContext().resources.getString(R.string.button_next_question)
                else -> requireContext().resources.getString(R.string.button_submit_answers)
            }
    }

    override fun setPreviousQuestionAccessMode() {
        binding.previousBtn.setAccessModeBy { questionId != 1 }
    }

    override fun setOnNextButtonClickListener() {
        binding.apply {
            nextBtn.setOnClickListener {
                presenter.onShowNextFragment()
            }
        }
    }

    override fun setOnPreviousButtonClickListener() {
        binding.apply {
            previousBtn.setOnClickListener {
                presenter.onShowPreviousFragment()
            }
        }
    }

    private fun Button.setAccessModeBy(predicate: () -> Boolean) {
        isEnabled = predicate.invoke()
    }
}