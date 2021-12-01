package com.test.bookmyseat.ui.activities

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.bookmyseat.R
import com.test.bookmyseat.ui.fragments.MovieDetailFragment
import com.test.bookmyseat.ui.fragments.MovieListingFragment

abstract class BaseActivity : AppCompatActivity() {
    abstract fun layoutRes(): Int
    abstract fun initViews()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCutoutView()
        setContentView(layoutRes())
        initProgressDialog()
        initViews()
    }

    protected fun replaceFragment(containerViewId: Int, fragment: Fragment?, fragmentTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.do_nothing_animation, R.anim.do_nothing_animation, R.anim.exit_to_bottom)
        if (fragment is MovieDetailFragment) {
            fragmentTransaction.add(containerViewId, fragment!!, fragmentTag)
        }
        else {
            fragmentTransaction.replace(containerViewId, fragment!!, fragmentTag)
        }
        if (fragment !is MovieListingFragment)fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setCancelable(false)
        progressDialog?.setTitle("loading")
    }

    fun showProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                hideProgressDialog()
            }
            progressDialog!!.show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    protected fun setCutoutView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }
}

