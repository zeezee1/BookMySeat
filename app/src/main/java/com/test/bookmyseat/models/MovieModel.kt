package com.test.bookmyseat.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Movies")
class MovieModel: Serializable {

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("adult")
    @Expose
    var adult = false

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id = 0

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity = 0f

    @SerializedName("vote_count")
    @Expose
    var voteCount = 0

    @SerializedName("video")
    @Expose
    var video = false

    @SerializedName("vote_average")
    @Expose
    var voteAverage = 0f

    var movieDetails: MovieDetailsModel? = null

    fun getRating(): Float {
        return (voteAverage / 10f * 100) / 20f
    }

    fun getReleaseYear(): String {
        return releaseDate!!.split("-")[0]
    }

}