package fr.iut.tsf.viewmodel

import androidx.lifecycle.*
import fr.iut.tsf.data.TSFRepository
import fr.iut.tsf.model.Film
import kotlinx.coroutines.launch

class FilmViewModel(private val repository: TSFRepository) : ViewModel() {

    val allMovies: LiveData<List<Film>> = repository.allMoviesApi
    fun insert(film: Film) = viewModelScope.launch { repository.insert(film) }
    fun update(film: Film) = viewModelScope.launch { repository.update(film) }
    fun delete(film: Film) = viewModelScope.launch { repository.delete(film) }

    fun getFilm(id: Int): LiveData<Film>{
        repository.getMovieDetailFromManager(id)
        return repository.oneMovie
    }

    fun search(query: String): LiveData<List<Film>> {
        repository.searchMovieFromManager(query)
        return repository.allMoviesSearch
    }
}

class FilmViewModelFactory(private val repository: TSFRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}