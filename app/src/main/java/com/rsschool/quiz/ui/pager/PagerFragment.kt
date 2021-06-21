package com.rsschool.quiz.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentPagerBinding

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
        presenter.initViewPager()
    }

    override fun setViewPager() {
        binding?.pager?.adapter = PagerAdapter(
            this, presenter.createQuestionsFragments()
        )
    }
}