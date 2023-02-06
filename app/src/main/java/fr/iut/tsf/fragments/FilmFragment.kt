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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory

@Suppress("UNREACHABLE_CODE")
class FilmFragment : Fragment() {
    private lateinit var film: LiveData<Film>
    private var filmId = 0
    private val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar_activity)
    private val PATH = "https://image.tmdb.org/t/p/w500/"
    private var listener: OnInteractionListener? = null

    companion object {
        private const val EXTRA_FILM_ID = "extrafilmid"
        fun newInstance(filmId: Int) =
            FilmFragment().apply {
                arguments = bundleOf(EXTRA_FILM_ID to filmId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId =
            savedInstanceState?.getInt(EXTRA_FILM_ID) ?: arguments?.getInt(EXTRA_FILM_ID) ?: filmId
        val viewModel =
            FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(
                FilmViewModel::class.java
            )
        film = viewModel.getFilm(filmId)
        Log.d("FilmFragment", "onCreate: $filmId ${film.value?.title} ${film.value?.description}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data, container, false)
        val backdrop: ImageView = view.findViewById(R.id.backdrop)
        val poster: ImageView = view.findViewById(R.id.poster)
        val title: TextView = view.findViewById(R.id.title)
        val overview: TextView = view.findViewById(R.id.overview)
        val date: TextView = view.findViewById(R.id.date)
        val rating: RatingBar = view.findViewById(R.id.rating)

        Glide.with(view).load(PATH + film.value!!.path)
            .transform(CenterCrop())
            .into(poster)
        Glide.with(view).load(PATH + film.value!!.backdropPath)
            .transform(CenterCrop())
            .into(backdrop)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = film.value!!.title
        title.text = film.value!!.title
        overview.text = film.value?.overview
        date.text = film.value?.releaseDate
        rating.rating = film.value?.voteAverage!!.toFloat() / 2
        view.findViewById<FloatingActionButton>(R.id.favButton).setOnClickListener {
            listener?.onAddFavoris(film.value!!)
            Toast.makeText(this.context, getString(R.string.addFavoris), Toast.LENGTH_SHORT).show()
        }
        return view
    }

    interface OnInteractionListener {
        fun onAddFavoris(film: Film)
    }
}
