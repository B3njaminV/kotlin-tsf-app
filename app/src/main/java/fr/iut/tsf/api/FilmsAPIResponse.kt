package fr.iut.tsf.api;

import com.google.gson.annotations.SerializedName

data class FilmsAPIResponse(
        @SerializedName("results") val movies: List<FilmAPI>
)
