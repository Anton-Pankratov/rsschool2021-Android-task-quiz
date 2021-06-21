package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.rsschool.quiz.R
import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.pager.OnCurrentFragmentListener
import com.rsschool.quiz.ui.pager.PagerPresenter
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.utils.DISABLED
import com.rsschool.quiz.utils.ENABLED
import com.rsschool.quiz.utils.NEXT
import com.rsschool.quiz.utils.PREVIOUS
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val presenter = MainPresenter(this)

    private var _pagerPresenter: PagerPresenter? = null
    private val pagerPresenter get() = _pagerPresenter

    private var repository: IRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = QuizRepository()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViews()
    }

    private fun setViews() {
        setOnNextQuestionClick()
        setOnPreviousQuestionClick()
    }

    override fun setPagerPresenter(pagerPresenter: PagerPresenter) {
        _pagerPresenter = pagerPresenter
        setViewsByCurrentFragment()
    }

    override fun setViewsByCurrentFragment() {
        pagerPresenter?.initOnCurrentFragmentListener(object : OnCurrentFragmentListener {
            override fun onCurrentFragment(id: Int) {
                binding?.apply {
                    toolbar.setQuestionNumber(id)
                    if (id == 0) {
                        previousButton.isEnabled = false
                        toolbar.setAccessMode(DISABLED)

                    } else {
                        previousButton.isEnabled = true
                        toolbar.setAccessMode(ENABLED)
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
}