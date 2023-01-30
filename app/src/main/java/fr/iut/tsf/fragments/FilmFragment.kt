package fr.iut.tsf.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory

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

        val parentActivity = requireActivity()

        val viewModel = FilmViewModelFactory(
            (requireActivity().application as TSFApplication).repository)
            .create(FilmViewModel::class.java)
        film = viewModel.getFilm(filmId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        return view
    }
}
