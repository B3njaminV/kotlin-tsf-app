package fr.iut.tsf.data

import androidx.annotation.WorkerThread
import fr.iut.tsf.model.Film
import kotlinx.coroutines.flow.Flow

class TSFRepository(private val tsfDAO: TSFDao){

    val allMovie: Flow<List<Film>> = tsfDAO.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(film: Film) {
        tsfDAO.insert(film)
    }

}