package fr.iut.tsf.viewmodel

import androidx.lifecycle.*
import fr.iut.tsf.data.TSFRepository
import fr.iut.tsf.model.Film

class FilmViewModel(private val repository: TSFRepository) : ViewModel() {

    val allMovies: LiveData<List<Film>> = repository.allMovie
    fun getFilm(id: Int): LiveData<Film>{
        return MutableLiveData(allMovies.value?.find { it.id == id })
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