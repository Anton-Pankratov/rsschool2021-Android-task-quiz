package com.rsschool.quiz.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.ui.utils.NEXT
import com.rsschool.quiz.ui.utils.PREVIOUS

class MainActivity : AppCompatActivity(), MainContract.View {

    /** ViewBinding conditionally needs to be nullified in the fragment (ONLY) */
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var _presenter: MainContract.Presenter? = null
    private val presenter get() = _presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setPresenter()

        presenter?.apply {
            onSetViewPager()
            listenQuestionAction()
        }
    }

    override fun setPresenter() {
        _presenter = MainPresenter(this).also {
            it.initFragmentFactory(supportFragmentManager)
        }
    }

    override fun setFragmentsPager() {
        binding.pager.apply {
            presenter.let {
                isUserInputEnabled = false
                adapter = it?.onCreatePagerAdapter(this@MainActivity)
            }
        }
    }

    override fun setQuestionAction(action: String) {
        binding.pager.apply {
            when (action) {
                PREVIOUS -> currentItem -= 1
                NEXT -> currentItem += 1
            }
        }
    }
}