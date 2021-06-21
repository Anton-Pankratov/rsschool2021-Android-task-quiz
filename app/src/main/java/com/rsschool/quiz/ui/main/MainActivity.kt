package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rsschool.quiz.data.Storage
import com.rsschool.quiz.data.repository.QuizRepository
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.question.QuestionFragment
import java.util.*
import kotlin.collections.ArrayDeque
import com.rsschool.quiz.utils.FragmentTransactionAction as Action

class MainActivity : AppCompatActivity(), MainContract.View {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val presenter = MainPresenter(this)
    private val repository = QuizRepository()

    private val fragmentContainerId
        get() = binding?.container?.id

    val userAnswerIds = ArrayDeque<Pair<Int, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = QuizFragmentFactory()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViews()
    }

    override fun showFirstQuestionScreen() {
        QuestionFragment().apply {
            userAnswerIds.addFirst(this.id to -1)
            Action.ADD_FIRST.makeTransactionWith(this)
        }
    }

    override fun showNextQuestionScreen() {
        QuestionFragment().apply {
            userAnswerIds.add(this.id to -1)
            Action.REPLACE.makeTransactionWith(this)
        }
    }

    override fun showPreviousQuestionScreen() {
        supportFragmentManager.findFragmentByTag("this").apply {
            userAnswerIds.remove(this?.id to -1 )
            Action.REMOVE.makeTransactionWith(this as QuestionFragment)
        }
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

    private fun Action.makeTransactionWith(fragment: QuestionFragment) {
        supportFragmentManager.beginTransaction().apply {
            when (this@makeTransactionWith) {
                Action.ADD_FIRST, Action.REPLACE -> {
                    fragmentContainerId?.let { add(it, fragment) }
                    addToBackStack(null)
                }
                Action.REMOVE -> fragmentContainerId?.let { replace(it, fragment) }
            }
            commit()
        }
    }
}