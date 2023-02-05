package fr.iut.tsf.comparator

import androidx.recyclerview.widget.DiffUtil
import fr.iut.tsf.model.Film

class FilmComparator(): DiffUtil.ItemCallback<Film>(){
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

}