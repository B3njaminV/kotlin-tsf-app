package fr.iut.tsf.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.tsf.R
import fr.iut.tsf.fragments.DataFragment
import fr.iut.tsf.fragments.DataListFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity
import java.lang.reflect.Array.newInstance

class DataActivity : SimpleFragmentActivity()
/*DataFragment.OnInteractionListener */{

    //private var filmId =
    override fun createFragment() = DataListFragment().also { it }
    override fun getLayoutResId() = R.layout.toolbar

    companion object {
        private const val MOVIE_ID = "fr.iut.ouafff"

        fun getIntent(context: Context, id: Int) =
            Intent(context, DataActivity::class.java).apply {
                putExtra(MOVIE_ID, id)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




}
