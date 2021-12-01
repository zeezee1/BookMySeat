package com.test.bookmyseat.models

import com.google.gson.annotations.SerializedName

class MoviesListResponse(@SerializedName("results") var movies: ArrayList<MovieModel>)