package com.el_grocer.shopper.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MovieModel

@Dao
interface MovieDao {
    @Query("DELETE FROM Movies")
    fun deleteAll()

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): List<MovieModel>

    @Query("SELECT movieDetails FROM Movies WHERE id = :movieId")
    fun getMovieDetails(movieId: Int):  MovieDetailsModel?

    @Query("UPDATE Movies SET movieDetails = :movieDetails WHERE id = :movieId")
    fun updateMovieDetails(movieDetails: MovieDetailsModel, movieId: Int):  Int

    @Insert(onConflict = IGNORE)
    fun saveMovie(vararg movie: MovieModel)
}