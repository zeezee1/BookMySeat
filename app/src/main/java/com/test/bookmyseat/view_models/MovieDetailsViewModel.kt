package com.test.bookmyseat.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.el_grocer.shopper.database.room.DatabaseManager
import com.test.bookmyseat.Constants.HIDE_PROGRESS
import com.test.bookmyseat.Constants.SHOW_PROGRESS
import com.test.bookmyseat.MyApplication
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MovieModel
import com.test.bookmyseat.models.MoviesListResponse
import com.test.bookmyseat.webcalls.RestClientManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel: BaseViewModel() {

    private var mMovie = MutableLiveData<MovieDetailsModel>()
    var movie: LiveData<MovieDetailsModel?> = mMovie

    fun getMovieDetails(movieId: Int) {
        apiClient.getMovieDetails(movieId, object: Callback<MovieDetailsModel> {
            override fun onResponse(call: Call<MovieDetailsModel>, response: Response<MovieDetailsModel>) {
                if(response.body() != null) {
                    val movieDetails: MovieDetailsModel = response.body()!!
                    DatabaseManager.getInstance().updateMovieDetails(movieId, movieDetails)
                    mMovie.postValue(movieDetails)
                } else {
                    mMovie.postValue(getLocalMovieDetails(movieId))
                }
            }

            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                mMovie.postValue(getLocalMovieDetails(movieId))
            }

        })
    }

    private fun getLocalMovieDetails(movieId: Int): MovieDetailsModel? {
        return DatabaseManager.getInstance().getMovieDetails(movieId)
    }
}