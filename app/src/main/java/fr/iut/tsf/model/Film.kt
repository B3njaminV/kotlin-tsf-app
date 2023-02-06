package fr.iut.tsf.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "film")
data class Film(
    @PrimaryKey()
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val path: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("popularity") val popularity: String?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("backdrop_path") val backdropPath: String?
)
