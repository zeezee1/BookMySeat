package com.test.bookmyseat.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.el_grocer.shopper.database.room.DatabaseManager
import com.test.bookmyseat.Constants.HIDE_PROGRESS
import com.test.bookmyseat.Constants.SHOW_PROGRESS
import com.test.bookmyseat.MyApplication
import com.test.bookmyseat.models.MovieModel
import com.test.bookmyseat.models.MoviesListResponse
import com.test.bookmyseat.webcalls.RestClientManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListViewModel : BaseViewModel() {

    private var mMovies = MutableLiveData<ArrayList<MovieModel>>()
    var movies: LiveData<ArrayList<MovieModel>> = mMovies

    fun getMovies(showLoading: Boolean = true) {
        if (showLoading)
            loading(SHOW_PROGRESS)
        apiClient.getMovieList(object : Callback<MoviesListResponse> {
            override fun onResponse(call: Call<MoviesListResponse>, response: Response<MoviesListResponse>) {
                loading(HIDE_PROGRESS)
                if (response.body() == null) {
                    mMovies.postValue(getLocalMovies())
                } else {
                    val moviesListResponse: MoviesListResponse = response.body()!!
                    DatabaseManager.getInstance().saveMovies(moviesListResponse.movies)
                    mMovies.postValue(moviesListResponse.movies)
                }
            }

            override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                loading(HIDE_PROGRESS)
                mMovies.postValue(getLocalMovies())
            }

        })
    }

    private fun getLocalMovies(): ArrayList<MovieModel> {
        return DatabaseManager.getInstance().getMovies() as ArrayList<MovieModel>
    }
}