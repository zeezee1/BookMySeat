package com.el_grocer.shopper.database.room

import androidx.room.Room
import com.test.bookmyseat.MyApplication
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MovieModel

class DatabaseManager {

    private var database: MyDatabase = Room.databaseBuilder(MyApplication.getInstance().applicationContext, MyDatabase::class.java, "RoomDatabase").allowMainThreadQueries().build()

    companion object {
        private var instant: DatabaseManager? = null

        public fun getInstance(): DatabaseManager {
            if (instant == null) {
                instant = DatabaseManager()
            }
            return instant!!
        }
    }

    fun getMovies(): List<MovieModel> {
        return database.getMovieDao().getAllMovies()
    }

    fun updateMovieDetails(movieId: Int, movieDetails: MovieDetailsModel) {
        database.getMovieDao().updateMovieDetails(movieDetails, movieId)
    }

    fun getMovieDetails(movieId: Int): MovieDetailsModel? {
        return database.getMovieDao().getMovieDetails(movieId)
    }

    fun saveMovies(movies: ArrayList<MovieModel>) {
        /*val array: Array<MovieModel> = emptyArray()
        movies.forEachIndexed { index, movieModel ->
            array[index] = movieModel
        }*/
        return database.getMovieDao().saveMovie(*(movies.toTypedArray()))
    }

}