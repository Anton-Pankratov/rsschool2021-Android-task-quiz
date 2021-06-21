package com.rsschool.quiz.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentPagerBinding
import com.rsschool.quiz.ui.base.BaseFragment
import com.rsschool.quiz.ui.main.MainActivity
import com.rsschool.quiz.ui.main.OnChangePageListener
import com.rsschool.quiz.utils.NEXT
import com.rsschool.quiz.utils.PREVIOUS
import com.rsschool.quiz.utils.SUBMIT

class PagerFragment : BaseFragment<FragmentPagerBinding>(), PagerContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPagerBinding
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

    override fun setPagerPresenterInActivity() {
        (activity as MainActivity).setPagerPresenter(presenter)
    }

    override fun setChangePageAction() {
        (activity as MainActivity).setOnChangePageListener(object : OnChangePageListener {
            override fun onChangePage(action: String) {
                binding.pager.apply {
                    when (action) {
                        NEXT -> currentItem += 1
                        PREVIOUS ->  currentItem -= 1
                        SUBMIT -> currentItem = fragmentsCount - 1
                    }
                }
            }
        })
    }
}