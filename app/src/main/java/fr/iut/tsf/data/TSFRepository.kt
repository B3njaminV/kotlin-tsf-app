package fr.iut.tsf.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.FilmsAPIResponse
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.model.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TSFRepository(private val tsfDAO: TSFDao, private val tsfManager: TSFManager){

    val allMoviesDb = tsfDAO.getAll().asLiveData()
    val allMoviesApi = MutableLiveData<List<Film>>()

    init {
        CoroutineScope(Dispatchers.IO).launch { getAllPopularMovieDbFromManager() }
    }
    suspend fun insert(film: Film) = withContext(Dispatchers.IO) { Log.i("Repository", "INSERT"); tsfDAO.insert(film) }
    suspend fun delete(film: Film) = withContext(Dispatchers.IO) { Log.i("Repository", "DELETE"); tsfDAO.delete(film) }
    suspend fun update(film: Film) = withContext(Dispatchers.IO) { Log.i("Repository", "UPDATE"); tsfDAO.update(film) }

    private suspend fun getAllPopularMovieDbFromManager() {
        tsfManager.getPopMovies().enqueue(object : Callback<FilmsAPIResponse> {
            override fun onResponse(call: Call<FilmsAPIResponse>, response: Response<FilmsAPIResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("Repository", "Movies : ${responseBody.movies}")
                        allMoviesApi.postValue(response.body()?.movies)
                    } else {
                        Log.d("Repository", "Failed to get response")
                    }
                }
            }

            override fun onFailure(call: Call<FilmsAPIResponse>, t: Throwable) {
                Log.e("Repository", "Failure", t)
            }
        })
    }
}