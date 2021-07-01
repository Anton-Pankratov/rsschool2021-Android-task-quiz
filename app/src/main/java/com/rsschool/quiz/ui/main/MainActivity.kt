package com.rsschool.quiz.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.utils.Action
import com.rsschool.quiz.utils.Action.*

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
            onSetFlags()
            onSetViewPager()
        }
    }

    override fun setWindowFlags() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
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
                setPagerAdapter()
            }
        }
    }

    override fun setAction(action: Action, param: Any?) {
        when (action) {
            NEXT, SUBMIT -> binding.pager.currentItem += 1
            PREVIOUS -> binding.pager.currentItem -= 1
            REPEAT -> binding.pager.setPagerAdapter()
            SHARE -> startActivity(param as Intent)
            EXIT -> finishAndRemoveTask()
        }
    }

    private fun ViewPager2.setPagerAdapter() {
        adapter = presenter?.onCreatePagerAdapter(this@MainActivity)
    }
}