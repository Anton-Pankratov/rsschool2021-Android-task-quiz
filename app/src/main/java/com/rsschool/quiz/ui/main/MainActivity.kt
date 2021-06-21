package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.data.repository.IRepository
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.question.QuestionFragment
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val presenter = MainPresenter(this)
    private var repository: IRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = QuizFragmentFactory()
        repository = QuizRepository()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViews()
    }

    override fun showNextQuestionScreen() {

    }

    override fun showPreviousQuestionScreen() {

    }

    private fun setViews() {
        setOnNextQuestionClick()
        setOnPreviousQuestionClick()
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