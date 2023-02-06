package fr.iut.tsf.api
import fr.iut.tsf.model.Film
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun getPopMovies(): Call<FilmsAPIResponse> {
        return service.getPopularMovies(APIKEY, LANGUAGE, PAGE);
    }
    fun getDetailMovie(id : Int): Call<Film> {
        return service.getMovie(APIKEY, id , LANGUAGE);
    }

}