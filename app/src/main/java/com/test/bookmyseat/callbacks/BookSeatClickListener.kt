package com.test.bookmyseat.callbacks

import com.test.bookmyseat.models.MovieModel

interface BookSeatClickListener {
    fun onBookSeatClicked(movie: MovieModel)
}