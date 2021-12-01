package com.test.bookmyseat.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.test.bookmyseat.R
import com.test.bookmyseat.callbacks.FragmentBehavior
import com.test.bookmyseat.ui.fragments.MovieListingFragment

class MainActivity : BaseActivity(), FragmentBehavior {

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    var tvTitle: TextView? = null

    override fun initViews() {
        tvTitle = findViewById(R.id.tvTitle)
        iReplaceFragment(MovieListingFragment.getInstance())
    }

    override fun iReplaceFragment(fragment: Fragment) {
        replaceFragment(R.id.flContainer, fragment, fragment::class.java.simpleName)
    }

}