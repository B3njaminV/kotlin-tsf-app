package fr.iut.tsf.api

import androidx.lifecycle.LiveData
import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.Film
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TSFServices {
    @GET("")
    fun getMovie(@Query("idBalise") idBalise: Int): Call<Film>;

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "api_key=e18509c478bab6b0ec871400a09d9daa",
        @Query("page") page: Int
    ): Call<Flow<List<FilmEntity>>>

}