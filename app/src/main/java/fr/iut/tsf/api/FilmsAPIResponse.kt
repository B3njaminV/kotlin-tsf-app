package fr.iut.tsf.api;

import com.google.gson.annotations.SerializedName
import fr.iut.tsf.model.Film

data class FilmsAPIResponse(
        @SerializedName("results") val movies: List<Film>
)
