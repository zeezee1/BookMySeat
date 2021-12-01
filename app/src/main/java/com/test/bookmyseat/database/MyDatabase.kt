package com.el_grocer.shopper.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.bookmyseat.models.MovieModel

@Database(entities = [MovieModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}