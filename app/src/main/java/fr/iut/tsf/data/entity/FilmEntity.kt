package fr.iut.tsf.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(@PrimaryKey(autoGenerate = true) val id: Int, val nom: String, val path: String, val note: Double)