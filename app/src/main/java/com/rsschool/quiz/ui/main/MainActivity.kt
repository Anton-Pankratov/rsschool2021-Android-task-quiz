package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter
import com.rsschool.quiz.utils.*
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null

    private val presenter = MainPresenter(this)

    private var _pagerPresenter: PagerPresenter? = null
    private val pagerPresenter get() = _pagerPresenter

    private var currentFragmentId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setViews() {
        setOnNavigationIconClickListener()
        setOnNextQuestionClick()
        setOnPreviousQuestionClick()
    }

    override fun setOnNavigationIconClickListener() {
        binding?.toolbar?.apply {
            setSupportActionBar(this)
            setNavigationOnClickListener {
                if (currentFragmentId != 0) {
                    presenter.setOnChangePageAction(PREVIOUS)
                }
            }
        }
    }

    override fun setPagerPresenter(pagerPresenter: PagerPresenter) {
        _pagerPresenter = pagerPresenter
        setViewsByCurrentFragment()
    }

    override fun setOnChangePageListener(listener: OnChangePageListener) {
        presenter.initOnChangePageListener(listener)
    }

    override fun showNextQuestionPage() {
        presenter.setOnChangePageAction(NEXT)
    }

    override fun showPreviousQuestionPage() {
        presenter.setOnChangePageAction(PREVIOUS)
    }

    private fun setOnNextQuestionClick() {
        binding?.nextButton?.setOnClickListener {
            presenter.listenOnNextQuestionClick()
        }
    }

    private fun setOnPreviousQuestionClick() {
        binding?.previousButton?.setOnClickListener {
            presenter.listenOnPreviousQuestionClick()
        }
    }

    override fun getSignalAboutAnswerSelected() {
        if (binding?.nextButton?.isEnabled == false) {
            binding?.nextButton?.isEnabled = true
        }
    }

    override fun setViewsByCurrentFragment() {
        pagerPresenter?.initOnCurrentFragmentListener(object : OnCurrentFragmentListener {
            override fun onCurrentFragment(questionId: Int) {
                currentFragmentId = questionId
                val questions = presenter.getAnswersList()
                if (!questions.isNullOrEmpty()) {

                    binding?.apply {

                        toolbar.setQuestionNumber(questionId)
                        nextButton.setAccessModeBy { questions[questionId] != -1 }

                        if (questionId == 0) {
                            previousButton.setAccessMode(DISABLED)
                            toolbar.setAccessMode(DISABLED)
                        } else {
                            previousButton.setAccessMode(ENABLED)
                            toolbar.setAccessMode(ENABLED)
                            nextButton.setButtonActionName(
                                if (questionId == questions.size - 1)
                                    SUBMIT else NEXT
                            )
                        }
                    }
                }
            }
        })
    }

    private fun Toolbar.setAccessMode(mode: String) {
        navigationIcon = ContextCompat.getDrawable(
            context,
            if (mode == ENABLED) {
                R.drawable.ic_toolbar_back_enabled
            } else {
                R.drawable.ic_toolbar_back_disabled
            }
        )
    }

    private fun Toolbar.setQuestionNumber(number: Int) {
        title = String.format(
            this@MainActivity.resources
                .getString(R.string.toolbar_title), number + 1
        )
    }

    private fun Button.setAccessMode(mode: String) {
        isEnabled = mode == ENABLED
    }

    private fun Button.setAccessModeBy(predicate: () -> Boolean) {
        isEnabled = predicate.invoke()
    }

    private fun Button.setButtonActionName(name: String) {
        this@MainActivity.resources.apply {
            text = if (name == SUBMIT) {
                getString(R.string.button_submit_answers)
            } else {
                getString(R.string.button_next_question)
            }
        }
    }
}