package fr.iut.tsf.adapater

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.iut.tsf.R
import fr.iut.tsf.databinding.AdaptateurContenuBinding
import fr.iut.tsf.model.Film
import kotlinx.coroutines.flow.Flow

class AdaptateurContenu(private var dataList: LiveData<List<Film>>, private val listener: Callbacks) : RecyclerView.Adapter<AdaptateurContenu.FilmViewHolder>() {

    private var PATHFORPICTURE = "https://image.tmdb.org/t/p/w500"
    override fun getItemCount() = dataList.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmViewHolder(
        AdaptateurContenuBinding.inflate(LayoutInflater.from(parent.context)),
        listener
        )


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(dataList.value!![position])


    class FilmViewHolder(private val binding: AdaptateurContenuBinding, listener: Callbacks) : RecyclerView.ViewHolder(binding.root) {
        private var titre = itemView.findViewById<TextView>(R.id.titre)
        private var image = itemView.findViewById<ImageView>(R.id.imageView)

        var film: Film? = null
            private set

        init {
            itemView.setOnClickListener { film?.let { listener.onFilmSelect(it.id) } }
        }

        fun bind(film: Film) {
            this.film = film
            titre.text = film.title
            // Utilisation de Glide pour charger l'image
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/" + film.path).into(image)
        }
    }

    fun updateList(dataList: LiveData<List<Film>>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    interface Callbacks {
        fun onFilmSelect(id: Int)
    }
}
