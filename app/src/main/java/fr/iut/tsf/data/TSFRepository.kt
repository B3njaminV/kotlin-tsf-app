package fr.iut.tsf.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.Film

class TSFRepository(private val tsfDAO: TSFDao, private val tsfManager: TSFManager){

    private val moviesEntity = tsfDAO.getAll().asLiveData()
    private val moviesEntityRepo = tsfManager.getPopularMovies()

    val allMovies: LiveData<List<Film>> = Transformations.map(moviesEntity) { entities ->
        val list = mutableListOf<Film>()
        for (item: FilmEntity in entities) {
            list.add(Film(item.id, item.nom, item.path, item.note))
        }
        list.add(Film(0, "test", "test", 0.0))
        list.add(Film(1, "test", "test", 0.0))
        list.add(Film(2, "test", "test", 0.0))
        list.add(Film(3, "test", "test", 0.0))
        list.add(Film(4, "test", "test", 0.0))
        list.add(Film(5, "test", "test", 0.0))
        list.add(Film(6, "test", "test", 0.0))
        list.add(Film(7, "test", "test", 0.0))
        list.add(Film(8, "test", "test", 0.0))
        list.add(Film(9, "test", "test", 0.0))
        list.add(Film(10, "test", "test", 0.0))
        list.add(Film(11, "test", "test", 0.0))
        list.add(Film(12, "test", "test", 0.0))
        list.add(Film(13, "test", "test", 0.0))
        list.add(Film(14, "test", "test", 0.0))
        list.add(Film(15, "test", "test", 0.0))
        list
    }

    val allMoviesRepo: LiveData<List<Film>> = Transformations.map(moviesEntity) { entities ->
        val list = mutableListOf<Film>()
        for (item: FilmEntity in entities) {
            list.add(Film(item.id, item.nom, item.path, item.note))
        }
        list.add(Film(0, "test", "test", 0.0))
        list.add(Film(1, "test", "test", 0.0))
        list.add(Film(2, "test", "test", 0.0))
        list.add(Film(3, "test", "test", 0.0))
        list
    }
}