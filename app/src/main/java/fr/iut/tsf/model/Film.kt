package fr.iut.tsf.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val path: String,
    @SerializedName("note") val note: String,
)
