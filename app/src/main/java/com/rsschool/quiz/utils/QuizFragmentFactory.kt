package com.rsschool.quiz.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.rsschool.quiz.ui.question.QuestionFragment
import com.rsschool.quiz.ui.result.ResultFragment

object QuizFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            QuestionFragment::class.java.name -> QuestionFragment()
            ResultFragment::class.java.name -> ResultFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}