package fr.iut.tsf.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class FilmAPI() {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null

}