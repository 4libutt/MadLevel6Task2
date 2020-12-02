package com.example.madlevel6task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class MovieResponse() {

    data class Root (
        @SerializedName("results") val results : List<Movie>
    )

    @Parcelize
    data class Movie (
        @SerializedName("poster_path") val poster_path : String,
        @SerializedName("backdrop_path") val backdrop_path : String,
        @SerializedName("title") val title : String,
        @SerializedName("vote_average") val vote_average : Double,
        @SerializedName("overview") val overview : String,
        @SerializedName("release_date") val release_date : String
    ) : Parcelable {
        fun getMovieImageUrl(posterPath: String): String {
            return "https://image.tmdb.org/t/p/original%s".format(posterPath)
        }
    }

}