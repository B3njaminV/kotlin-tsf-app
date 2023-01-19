package fr.iut.tsf.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(val nom: String, val path: String, val note: Double, @PrimaryKey(autoGenerate = true) val id: Long = 0L)