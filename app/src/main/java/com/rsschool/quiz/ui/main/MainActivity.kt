package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.data.Storage
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.question.QuestionFragment

class MainActivity : AppCompatActivity(), MainContract.View {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val presenter = MainPresenter(this)
    private val repository = QuizRepository()

    private val fragmentContainerId
        get() = binding?.container?.id

    val userAnswerIds = MutableList(Storage.questions.size) { -1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = QuizFragmentFactory()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViews()
    }

    override fun showFirstQuestionScreen() {
        val transaction = supportFragmentManager.beginTransaction()
        fragmentContainerId?.let { transaction.add(it, QuestionFragment()) }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showQuestionScreen() {
        val transaction = supportFragmentManager.beginTransaction()
        fragmentContainerId?.let { transaction.replace(it, QuestionFragment(2)) }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun setViews() {
        presenter.startWithTheFirstQuestion()
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