package fr.iut.tsf.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import fr.iut.tsf.data.TSFRepository
import fr.iut.tsf.model.Film

class FilmViewModel(private val repository: TSFRepository) : ViewModel() {

    val movies: LiveData<List<Film>> = repository.allMovie.asLiveData()
    
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