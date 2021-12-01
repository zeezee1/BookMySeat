package com.test.bookmyseat.webcalls

import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {

    @GET("movie/popular")
    fun getMovies(): Call<MoviesListResponse>

    @GET("movie/{movie-id}")
    fun getMovieDetails(@Path("movie-id") movieId: Int): Call<MovieDetailsModel>
}