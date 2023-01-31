package fr.iut.tsf.ui;

import android.os.Bundle
import android.view.WindowManager
import fr.iut.tsf.R
import fr.iut.tsf.fragments.FilmListFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity

class FilmListActivity : SimpleFragmentActivity(), FilmListFragment.OnInteractionListener {

    private lateinit var master: FilmListFragment

    override fun createFragment() = FilmListFragment().also { master = it }

    override fun getLayoutResId(): Int =  R.layout.toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (savedInstanceState != null)
            master = supportFragmentManager.findFragmentById(R.id.container_fragment) as FilmListFragment

    }
    override fun onFilmSelect(id: Int) {
        startActivity(FilmActivity.getIntent(this, id))
    }
}

