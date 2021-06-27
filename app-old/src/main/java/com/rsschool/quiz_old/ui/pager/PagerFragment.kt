package com.rsschool.quiz_old.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentPagerBinding
import com.rsschool.quiz_old.utils.NEXT
import com.rsschool.quiz_old.utils.PREVIOUS
import com.rsschool.quiz_old.utils.SUBMIT
import com.rsschool.quiz_old.ui.base.BaseFragment
import com.rsschool.quiz_old.ui.main.MainActivity
import com.rsschool.quiz_old.utils.OnChangePageListener
import com.rsschool.quiz_old.utils.QuizFragmentFactory

class PagerFragment : BaseFragment<FragmentPagerBinding>(), PagerContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
        -> FragmentPagerBinding
            get() = FragmentPagerBinding::inflate

    override val presenter = PagerPresenter(this)

    private var fragmentsCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.fragmentFactory = QuizFragmentFactory()
        presenter.apply {
            initViewPager()
            providePagerPresenter()
            listenPageChangeAction()
        }
        setCurrentFragmentToListen()
    }

    override fun setViewPager() {
        presenter.createFragments().let { list ->
            fragmentsCount = list.size
            binding.pager.apply {
                isUserInputEnabled = false
                adapter = PagerAdapter(
                    this@PagerFragment, list
                )
            }
        }
    }

    override fun setCurrentFragmentToListen() {
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.pager.currentItem.let { presenter.setCurrentFragmentInListener(it) }
            }
        })
    }

    override fun setChangePageAction() {
        (activity as MainActivity).setOnChangePageListener(object : OnChangePageListener {
            override fun onChangePage(action: String) {
                binding.pager.apply {
                    when (action) {
                        NEXT -> currentItem += 1
                        PREVIOUS -> currentItem -= 1
                        SUBMIT -> currentItem = fragmentsCount - 1
                    }
                }
            }
        })
    }

    override fun setPagerPresenterInActivity() {
        (activity as MainActivity).setPagerPresenter(presenter)
    }
}