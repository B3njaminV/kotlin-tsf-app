package fr.iut.tsf.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import fr.iut.tsf.R
import fr.iut.tsf.fragments.FilmFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity

class FilmActivity : SimpleFragmentActivity() {
    override fun createFragment() = FilmFragment.newInstance(filmId)
    override fun getLayoutResId() = R.layout.toolbar

    private var filmId = 0

    companion object {
        private const val EXTRA_MOVIE_ID = "fr.iut.ouafff.extramovieid"
        fun getIntent(context: Context, id: Int) =
            Intent(context, FilmActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, id)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        filmId = intent.getIntExtra(EXTRA_MOVIE_ID, filmId)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
    }

}
