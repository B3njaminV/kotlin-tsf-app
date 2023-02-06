package fr.iut.tsf.api

import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TSFServices {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<FilmsAPIResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<FilmsAPIResponse>

    @GET("movie/{movieId}")
    fun getMovie(
        @Query("api_key") apiKey: String,
        @Path("movieId") movieId: Int,
        @Query("language") language: String,
    ): Call<Film>


}