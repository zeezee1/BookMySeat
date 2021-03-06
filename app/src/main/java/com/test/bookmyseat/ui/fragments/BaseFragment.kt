package com.test.bookmyseat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.test.bookmyseat.callbacks.FragmentBehavior
import com.test.bookmyseat.ui.activities.BaseActivity
import com.test.bookmyseat.ui.activities.MainActivity

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    abstract fun fragmentLayout(): Int
    abstract fun initViews()

    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    protected fun replaceFragment(fragment: Fragment) {
        (activity as FragmentBehavior).iReplaceFragment(fragment)
    }

    protected fun showProgressDialog() {
        (activity as BaseActivity).showProgressDialog()
    }

    protected fun hideProgressDialog() {
        (activity as BaseActivity).hideProgressDialog()
    }

}