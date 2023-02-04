package fr.iut.tsf.api

import android.graphics.Movie
import android.util.Log
import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Flow

class TSFManager {

    private val service : TSFServices
    private val URL = "https://api.themoviedb.org/3/"
    private val PAGE = 1
    private val LANGUAGE = "fr-FR"
    private val APIKEY = "e18509c478bab6b0ec871400a09d9daa"
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TSFServices::class.java);
    }

    fun getPopularMovies() : List<Film>{
        service.getPopularMovies(APIKEY, LANGUAGE, PAGE)
            .enqueue(object : Callback<FilmsAPIResponse> {
                override fun onResponse(
                    call: Call<FilmsAPIResponse>,
                    response: Response<FilmsAPIResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.d("Repository", "Movies: ${responseBody.movies}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                        return responseBody.movies
                    }
                }

                override fun onFailure(call: Call<FilmsAPIResponse>, t: Throwable) {
                    Log.e("Repository", "Failure", t)
                }
            })
    }
}