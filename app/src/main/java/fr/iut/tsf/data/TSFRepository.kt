package fr.iut.tsf.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.data.entity.FilmEntity
import fr.iut.tsf.model.Film

class TSFRepository(private val tsfDAO: TSFDao, private val TSFManager: TSFManager){

    private val moviesEntity = tsfDAO.getAll().asLiveData()

    val allMovies: LiveData<List<Film>> = Transformations.map(moviesEntity) { entities ->
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