package fr.iut.tsf.adapater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tsf.R
import fr.iut.tsf.model.Film
import kotlinx.coroutines.flow.Flow

class AdaptateurContenu(private var dataList: LiveData<List<Film>>, private val listener: Callbacks) :
    RecyclerView.Adapter<AdaptateurContenu.FilmViewHolder>() {

    private val PATH = "https://image.tmdb.org/t/p/w500"
    override fun getItemCount() = dataList.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilmViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.adaptateur_contenu,
                parent,
                false
            ), listener
        )


    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(dataList.value!![position])


    class FilmViewHolder(itemView: View, listener: Callbacks) : RecyclerView.ViewHolder(itemView) {

        //private var picture = itemView.findViewById<ImageView>(R.id.image)
        private var titre = itemView.findViewById<TextView>(R.id.titre)

        var film: Film? = null
            private set

        init {
            itemView.setOnClickListener { film?.let { listener.onFilmSelect(it.id) } }
        }

        fun bind(film: Film) {
            this.film = film
            titre.text = film.nom
            val context = itemView.context
        }
    }

    fun updateList(dataList: LiveData<List<Film>>) {
        this.dataList = dataList
        notifyDataSetChanged()
        // a changer
    }

    interface Callbacks {
        fun onFilmSelect(id: Int)
    }
}
