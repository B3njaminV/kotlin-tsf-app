package fr.iut.tsf.api

import android.telecom.Call
import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TSFManager {
    private val URL = "";

    fun getMovie(id: Int): Call<Film> {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val service = retrofit.create(TSFServices::class.java);

        val request = service.getBaliseInfo(id);
        return request;
    }
}