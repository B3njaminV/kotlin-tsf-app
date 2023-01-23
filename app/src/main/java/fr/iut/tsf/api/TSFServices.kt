package fr.iut.tsf.api

import android.telecom.Call
import androidx.room.Query
import fr.iut.tsf.model.Film

interface TSFServices {
    @GET("")
    fun getMovie(@Query("idBalise") idBalise: Int): Call<Film>;
}