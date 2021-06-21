package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter
import com.rsschool.quiz.utils.*
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null

    private val mainPresenter = MainPresenter(this)

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
                    mainPresenter.setOnChangePageAction(PREVIOUS)
                }
            }
        }
    }

    override fun setPagerPresenter(pagerPresenter: PagerPresenter) {
        _pagerPresenter = pagerPresenter
        setViewsByCurrentFragment()
    }

    override fun setOnChangePageListener(listener: OnChangePageListener) {
        mainPresenter.initOnChangePageListener(listener)
    }

    override fun showNextQuestionPage() {
        mainPresenter.setOnChangePageAction(
            if (currentFragmentId != mainPresenter.getAnswersList()?.size?.minus(1))
                NEXT else SUBMIT
        )
    }

    override fun showPreviousQuestionPage() {
        mainPresenter.setOnChangePageAction(PREVIOUS)
    }

    private fun setOnNextQuestionClick() {
        binding?.nextButton?.setOnClickListener {
            mainPresenter.listenOnNextQuestionClick()
        }
    }

    private fun setOnPreviousQuestionClick() {
        binding?.previousButton?.setOnClickListener {
            mainPresenter.listenOnPreviousQuestionClick()
        }
    }

    override fun setSignalAboutAnswerSelected() {
        binding?.nextButton?.apply {
            if (!isEnabled) isEnabled = true
        }
    }

    override fun setViewsByCurrentFragment() {
        pagerPresenter?.initOnCurrentFragmentListener(object : OnCurrentFragmentListener {
            override fun onCurrentFragment(questionId: Int) {
                val questions = mainPresenter.getAnswersList()
                if (!questions.isNullOrEmpty()) {
                    if (questionId <= questions.size.minus(1)) {
                        currentFragmentId = questionId

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
                    } else mainPresenter.makeViewsInvisibleOnResultPage()
                }
            }
        })
    }

    override fun setViewsInvisibleOnResultPage() {
        binding?.apply {
            appBarLayout.isVisible = false
            nextButton.isVisible = false
            previousButton.isVisible = false
        }
    }

    private fun Toolbar.setAccessMode(mode: String) {
        navigationIcon = ContextCompat.getDrawable(
            context,
            if (mode == ENABLED)
                R.drawable.ic_toolbar_back_enabled
            else
                R.drawable.ic_toolbar_back_disabled

        )
    }

    private fun Toolbar.setQuestionNumber(number: Int) {
        title = String.format(
            this@MainActivity
                .getStringResource(R.string.toolbar_title), number + 1
        )
    }

    private fun Button.setAccessMode(mode: String) {
        isEnabled = mode == ENABLED
    }

    private fun Button.setAccessModeBy(predicate: () -> Boolean) {
        isEnabled = predicate.invoke()
    }

    private fun Button.setButtonActionName(name: String) {
        this.text = getStringResource(
            if (name == SUBMIT)
                R.string.button_submit_answers
            else
                R.string.button_next_question
        )
    }
}