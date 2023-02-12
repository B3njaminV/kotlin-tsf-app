package fr.iut.tsf.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.databinding.FragmentDataBinding
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory
import java.util.TreeMap

class FilmFragment : Fragment() {
    private lateinit var film: LiveData<Film>
    private var filmId = 0
    private val PATH = "https://image.tmdb.org/t/p/w500/"
    private lateinit var viewModel: FilmViewModel

    companion object {
        private const val EXTRA_FILM_ID = "extrafilmid"
        fun newInstance(filmId: Int) =
            FilmFragment().apply {
                arguments = bundleOf(EXTRA_FILM_ID to filmId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId =  savedInstanceState?.getInt(EXTRA_FILM_ID) ?: arguments?.getInt(EXTRA_FILM_ID) ?: filmId
        viewModel = FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(
                FilmViewModel::class.java
        )
        film = viewModel.getFilm(filmId)
        Log.d("FilmFragment", "onCreate: $filmId ${film.value?.title} ${film.value?.description}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDataBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.film = film.value
        (activity as AppCompatActivity?)?.supportActionBar?.title = film.value?.title
        binding.rating.rating = film.value?.voteAverage?.toFloat() ?: 0f
        Glide.with(this).load(PATH + film.value?.path)
            .transform(CenterCrop())
            .into(binding.poster)
        Glide.with(this).load(PATH + film.value?.backdropPath)
            .transform(CenterCrop())
            .into(binding.backdrop)
        binding.favButton.setOnClickListener {
            film.value?.let { it1 -> viewModel.insert(it1) }
            Toast.makeText(this.context, getString(R.string.addFavoris), Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            film = viewModel.getFilm(filmId)
        }
    }
}
