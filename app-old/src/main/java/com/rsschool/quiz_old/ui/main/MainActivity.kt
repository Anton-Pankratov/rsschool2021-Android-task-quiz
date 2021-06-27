package com.rsschool.quiz_old.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rsschool.quiz.*
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz_old.ui.pager.PagerFragment
import com.rsschool.quiz_old.ui.pager.PagerPresenter
import com.rsschool.quiz_old.ui.result.ResultPresenter
import com.rsschool.quiz_old.utils.*
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var binding: ActivityMainBinding? = null

    private val mainPresenter = MainPresenter(this)

    private var _pagerPresenter: PagerPresenter? = null
    private val pagerPresenter get() = _pagerPresenter

    private var _resultPresenter: ResultPresenter? = null
    private val resultPresenter get() = _resultPresenter

    private var currentFragmentId = 1

    private var lastKeepColor = Colors.DEFAULT.resId

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
        mainPresenter.initNewInstanceOfPager()
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

    override fun showNextQuestionPage() {
        mainPresenter.setOnChangePageAction(
            if (currentFragmentId != mainPresenter.getAnswersList()?.size?.minus(1))
                NEXT else SUBMIT
        )
    }

    override fun showPreviousQuestionPage() {
        mainPresenter.setOnChangePageAction(PREVIOUS)
    }

    override fun setViewsByCurrentFragment() {
        pagerPresenter?.initOnCurrentFragmentListener(object : OnCurrentFragmentListener {
            override fun onCurrentFragment(questionId: Int) {
                val answers = mainPresenter.getAnswersList()
                if (!answers.isNullOrEmpty()) {
                    if (questionId <= answers.size.minus(1)) {
                        currentFragmentId = questionId.also {
                            it.setViewsByCurrentQuestionWith(answers)
                        }
                    } else mainPresenter.makeViewsInvisibleOnResultPage()
                }
            }
        })
    }

    override fun setSignalAboutAnswerNotSelected() {
        binding?.nextButton?.isEnabled = false
    }

    override fun setSignalAboutAnswerSelected() {
        binding?.nextButton?.apply {
            if (!isEnabled) isEnabled = true
        }
    }

    override fun setSignalAboutChangeFragmentTheme(@ColorRes theme: Int) {
        binding?.apply {
            arrayOf(previousButton, nextButton, toolbar).forEach {
                it.setNewBackground(lastKeepColor, theme)
            }
            lastKeepColor = theme
        }
    }

    override fun setOnClicksFromResultPage() {
        resultPresenter?.setOnResultButtonsClickListener(
            object : OnResultPageButtonsClickListener {
                override fun onRepeatButtonClick() {
                    mainPresenter.initNewInstanceOfPager()
                    resetQuiz()
                }

                override fun onExitButtonClick() {
                    mainPresenter.exitFromQuizApp()
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

    override fun setOnChangePageListener(listener: OnChangePageListener) {
        mainPresenter.initOnChangePageListener(listener)
    }

    override fun setPagerPresenter(pagerPresenter: PagerPresenter) {
        _pagerPresenter = pagerPresenter
        setViewsByCurrentFragment()
    }

    override fun setResultPresenter(resultPresenter: ResultPresenter) {
        _resultPresenter = resultPresenter
        mainPresenter.listenClicksFromResultPage()
    }

    override fun setNewInstanceOfPager() {
        supportFragmentManager.beginTransaction().apply {
            supportFragmentManager.popBackStack()
            binding?.fragmentContainer?.id?.let { containerId ->
                replace(containerId, PagerFragment())
            }
            commit()
        }
    }

    override fun closeMainActivity() = finishAndRemoveTask()

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

    private fun Int.setViewsByCurrentQuestionWith(answers: List<Int>) {
        binding?.apply {
            val questionId = this@setViewsByCurrentQuestionWith
            toolbar.setQuestionNumber(questionId)
            nextButton.setAccessModeBy { answers[questionId] != -1 }

            if (questionId == 0) {
                previousButton.setAccessMode(DISABLED)
                toolbar.setAccessMode(DISABLED)
            } else {
                previousButton.setAccessMode(ENABLED)
                toolbar.setAccessMode(ENABLED)
                nextButton.setButtonActionName(
                    if (questionId == answers.size - 1)
                        SUBMIT else NEXT
                )
            }
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
            if (name == SUBMIT) R.string.button_submit_answers else R.string.button_next_question
        )
    }

    private fun resetQuiz() {
        mainPresenter.resetAnswerList()

        binding?.apply {

            appBarLayout.isVisible = true

            previousButton.let {
                it.isEnabled = false
                it.isVisible = true
            }

            nextButton.let {
                it.text = getStringResource(R.string.button_next_question)
                it.isEnabled = false
                it.isVisible = true
            }
        }
    }
}