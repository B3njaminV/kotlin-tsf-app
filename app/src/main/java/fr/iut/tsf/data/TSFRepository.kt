package fr.iut.tsf.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.model.Film
import kotlinx.coroutines.flow.Flow

class TSFRepository(private val tsfDAO: TSFDao, private val TSFManager: TSFManager){

    private val movies = tsfDAO.getAll().asLiveData()

    val allMovies: LiveData<List<Film>> = Transformations.map(movies) { entities ->
        val list = mutableListOf<Film>()
        for (item: Film in entities) {
            list.add(Film(item.id, /*item.date,*/ item.duree))
        }

        list
    }
}