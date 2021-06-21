package com.rsschool.quiz.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.FragmentPagerBinding
import com.rsschool.quiz.ui.main.MainActivity
import com.rsschool.quiz.ui.main.OnChangePageListener
import com.rsschool.quiz.utils.NEXT

class PagerFragment : Fragment(), PagerContract.View {

    private var binding: FragmentPagerBinding? = null

    private val presenter = PagerPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(
            inflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.fragmentFactory = QuizFragmentFactory()
        presenter.initViewPager()
        setPagerPresenterInActivity()
        setCurrentFragmentToListen()
        setChangePageAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun setViewPager() {
        binding?.pager?.apply {
            isUserInputEnabled = false
            adapter = PagerAdapter(
                this@PagerFragment, presenter.createQuestionsFragments()
            )
        }
    }

    override fun setCurrentFragmentToListen() {
        binding?.pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding?.pager?.currentItem?.let { presenter.setCurrentFragmentInListener(it) }
            }
        })
    }

    override fun setPagerPresenterInActivity() {
        (activity as MainActivity).setPagerPresenter(presenter)
    }

    override fun setChangePageAction() {
        (activity as MainActivity).setOnChangePageListener(object : OnChangePageListener {
            override fun onChangePage(action: String) {
                binding?.pager?.apply {
                    if (action == NEXT) {
                        currentItem += 1
                    } else {
                        currentItem -= 1
                    }
                }
            }
        })
    }
}