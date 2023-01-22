package fr.iut.tsf.api

import android.telecom.Call
import androidx.room.Query
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TSFServices {
    @GET("")
    fun getMovie(@Query("idBalise") idBalise: Int): Call<Film>;
}