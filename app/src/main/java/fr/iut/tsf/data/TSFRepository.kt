package fr.iut.tsf.data

import androidx.lifecycle.asLiveData
import fr.iut.tsf.api.TSFManager

class TSFRepository(private val tsfDAO: TSFDao, private val tsfManager: TSFManager){

    val allMoviesDb = tsfDAO.getAll().asLiveData()
    val allMoviesApi = tsfManager.getPopularMovies()


}