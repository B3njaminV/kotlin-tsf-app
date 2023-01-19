package fr.iut.tsf.model

import androidx.room.*

@Entity(tableName = "film")
data class FilmEntity(@PrimaryKey(autoGenerate = true) val id: Int, val nom: String, val path: String, val note: Double)