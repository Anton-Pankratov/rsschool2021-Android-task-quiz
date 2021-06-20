package com.rsschool.quiz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.question.QuestionFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        startFirstQuestion()
    }

    private fun startFirstQuestion() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, QuestionFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun setPreviousQuestionClick() {

    }

    private fun setNextQuestionClick() {

    }
}