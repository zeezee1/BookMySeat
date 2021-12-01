package com.el_grocer.shopper.database.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.bookmyseat.models.Genre
import com.test.bookmyseat.models.MovieDetailsModel
import com.test.bookmyseat.models.MovieModel
import com.test.bookmyseat.models.ProductionCompany
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun stringToList(value: String): ArrayList<MovieModel> {
        val listType: Type = object : TypeToken<ArrayList<MovieModel>>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: ArrayList<MovieModel>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToCompanies(companies: String?): List<ProductionCompany>? {
        val listType: Type = object : TypeToken<List<ProductionCompany>>() {}.getType()
        return Gson().fromJson(companies, listType)
    }

    @TypeConverter
    fun companiesToString(list: List<ProductionCompany>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToGenres(genres: String?): List<Genre>? {
        val listType: Type = object : TypeToken<List<Genre>>() {}.getType()
        return Gson().fromJson(genres, listType)
    }

    @TypeConverter
    fun genresToString(list: List<Genre>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToDetail(details: String?): MovieDetailsModel? {
        return Gson().fromJson(details, MovieDetailsModel::class.java)
    }

    @TypeConverter
    fun detailToString(details: MovieDetailsModel?): String? {
        return Gson().toJson(details)
    }

}