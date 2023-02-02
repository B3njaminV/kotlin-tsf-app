package fr.iut.tsf.data

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.FilmAPI
import fr.iut.tsf.api.FilmsAPIResponse
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.Film

class TSFRepository(private val tsfDAO: TSFDao, private val tsfManager: TSFManager){

    private val moviesEntity = tsfDAO.getAll().asLiveData()
    private val filmsEntity : LiveData<List<Film>> = TODO()

    val allMovies: LiveData<List<Film>> = Transformations.map(moviesEntity) { entities ->
        val list = mutableListOf<Film>()
        for (item: FilmEntity in entities) {
            list.add(Film(item.id, item.nom, item.path, item.note))
        }
        list
    }

    val allMoviesApi = tsfManager.getPopularMovies {
        val list = mutableListOf<Film>()
        for (item: FilmAPI in it) {
            list.add(Film(item.id?.toInt() ?: 0, item.title, item.posterPath))
        }
        list
    }


}