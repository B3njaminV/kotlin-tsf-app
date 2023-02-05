package fr.iut.tsf.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.iut.tsf.R
import fr.iut.tsf.comparator.FilmComparator
import fr.iut.tsf.databinding.AdaptateurContenuBinding
import fr.iut.tsf.model.Film

class AdaptateurContenu(private val listener: Callbacks) :
    ListAdapter<Film, AdaptateurContenu.FilmViewHolder>(FilmComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmViewHolder(
        AdaptateurContenuBinding.inflate(LayoutInflater.from(parent.context)),
        listener
    )

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) = holder.bind(getItem(position))

    class FilmViewHolder(private val binding: AdaptateurContenuBinding, listener: Callbacks) : RecyclerView.ViewHolder(binding.root) {
        private var titre = itemView.findViewById<TextView>(R.id.titre)
        private var image = itemView.findViewById<ImageView>(R.id.imageView)
        private val PATHFORPICTURE = "https://image.tmdb.org/t/p/w500/"

        val film: Film? get() = binding.film

        init {
            itemView.setOnClickListener { film?.let { listener.onFilmSelect(it.id) } }
        }

        fun bind(film: Film) {
            binding.film = film
            binding.executePendingBindings()
            binding.titre = film.title
            // Utilisation de Glide pour charger l'image
            Glide.with(itemView).load(PATHFORPICTURE + film.path).into(binding.imageView)
        }
    }

    interface Callbacks {
        fun onFilmSelect(id: Int)
    }
}
