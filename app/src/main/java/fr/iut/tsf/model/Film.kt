package fr.iut.tsf.model

import com.google.gson.annotations.SerializedName

data class Film(val id: Int, val nom: String?, val path: String?, val note: Double? = 0.0)
