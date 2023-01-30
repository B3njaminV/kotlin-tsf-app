package fr.iut.tsf.ui;

import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import fr.iut.tsf.R
import fr.iut.tsf.fragments.DataFragment
import fr.iut.tsf.fragments.DataListFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity

class DataListActivity : SimpleFragmentActivity(), DataListFragment.OnInteractionListener {

    private lateinit var master: DataListFragment

    override fun createFragment() = DataListFragment().also { master = it }

    override fun getLayoutResId(): Int =  R.layout.toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        if (savedInstanceState != null)
            master = supportFragmentManager.findFragmentById(R.id.container_fragment) as DataListFragment

    }

    override fun onFilmSelect(id: Int) {
        startActivity(DataActivity.getIntent(this, id))
    }

    override fun mesFavoris() {
        // affiche les favoris
    }
}

