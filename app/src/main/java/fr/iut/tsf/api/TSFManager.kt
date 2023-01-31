package fr.iut.tsf.api

import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TSFManager {

    var api = "https://api.themoviedb.org/3";
    var apiKey = "api_key=e18509c478bab6b0ec871400a09d9daa";
    var lang = "&language=fr-FR";
/*
    fun getMovieByName(query: String): Call<Film>{
        val retrofit = Retrofit.Builder()
            .baseUrl(api + "/search/movie?" + apiKey + lang + "&query=" + query )
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val service = retrofit.create(TSFServices::class.java);
        //val requete = service.getMovie(query);
        //return requete;
    }
    */
}