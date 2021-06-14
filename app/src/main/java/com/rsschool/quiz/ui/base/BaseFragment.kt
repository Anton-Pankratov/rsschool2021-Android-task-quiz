package com.rsschool.quiz.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    val binding
        get() = _binding ?: throw IllegalStateException(
            "Cannot access view in after view destroyed and before view creation"
        )

    open val viewModel: BaseViewModel by inject()

    private var _binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): B
}