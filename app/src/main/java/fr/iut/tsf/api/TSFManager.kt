package fr.iut.tsf.api

import android.util.Log
import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TSFManager {

    private val service : TSFServices
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TSFServices::class.java);
    }

    fun getPopularMovies(page: Int = 1) {
        service.getPopularMovies(page = page)
            .enqueue(object : Callback<Film> {
                override fun onResponse(
                    call: Call<Film>,
                    response: Response<Film>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.d("Repository", "Movies: OK")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}