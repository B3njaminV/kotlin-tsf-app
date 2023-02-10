package fr.iut.tsf.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import fr.iut.tsf.R
import fr.iut.tsf.fragments.FilmFragment
import fr.iut.tsf.fragments.FilmListFragment
import fr.iut.tsf.fragments.RechercheFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity

class RechercheActivity : SimpleFragmentActivity(), RechercheFragment.OnInteractionListener {

    private lateinit var master: RechercheFragment
    override fun createFragment() = RechercheFragment().also { master = it }
    override fun getLayoutResId() = R.layout.toolbar


    companion object {
        fun getIntent(context: Context, id: Int) =
            Intent(context, RechercheActivity::class.java).apply {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
    }

    override fun onFilmSelect(id: Int) {
        startActivity(FilmActivity.getIntent(this, id))
    }

}