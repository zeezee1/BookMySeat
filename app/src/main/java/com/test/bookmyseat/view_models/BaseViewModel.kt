package com.test.bookmyseat.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bookmyseat.MyApplication
import com.test.bookmyseat.webcalls.RestClientManager

open class BaseViewModel: ViewModel() {
    protected val apiClient = RestClientManager.getInstance(MyApplication.getInstance().applicationContext)

    private var mLoadingState = MutableLiveData<Int>()
    var loadingState = mLoadingState

    fun loading(loadingState: Int) {
        mLoadingState.postValue(loadingState)
    }

}