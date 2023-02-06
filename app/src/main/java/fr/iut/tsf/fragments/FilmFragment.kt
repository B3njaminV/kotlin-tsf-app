package fr.iut.tsf.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory

@Suppress("UNREACHABLE_CODE")
class FilmFragment : Fragment() {
    private lateinit var film: LiveData<Film>
    private var filmId = 0

    companion object {
        private const val EXTRA_FILM_ID = "extrafilmid"
        fun newInstance(filmId: Int) =
            FilmFragment().apply {
                arguments = bundleOf(EXTRA_FILM_ID to filmId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = savedInstanceState?.getInt(EXTRA_FILM_ID) ?: arguments?.getInt(EXTRA_FILM_ID) ?: filmId
        val viewModel = FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(FilmViewModel::class.java)
        film = viewModel.getFilm(filmId)
        Log.d("FilmFragment", "onCreate: $filmId ${film.value?.title} ${film.value?.description}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        val imageView2: ImageView = view.findViewById(R.id.imageView2)
        val detailtitre: TextView = view.findViewById(R.id.detailtitre)
        val description: TextView = view.findViewById(R.id.description)
        val genre : TextView = view.findViewById(R.id.genre)
        detailtitre.text = film.value?.title
        description.text = film.value?.description
        genre.text = film.value?.voteAverage
        Glide.with(view).load("https://image.tmdb.org/t/p/w500/" + film.value!!.path).into(imageView2)
        return view
    }
}
