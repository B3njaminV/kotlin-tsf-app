package fr.iut.tsf.api

import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TSFServices {
    @GET("")
    fun getMovie(@Query("idBalise") idBalise: Int): Call<Film>;

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<FilmsAPIResponse>

}