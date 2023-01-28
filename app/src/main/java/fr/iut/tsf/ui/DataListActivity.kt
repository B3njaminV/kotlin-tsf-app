package fr.iut.tsf.ui;

import androidx.fragment.app.Fragment
import fr.iut.tsf.R
import fr.iut.tsf.fragments.DataFragment
import fr.iut.tsf.fragments.DataListFragment
import fr.iut.tsf.ui.activity.SimpleFragmentActivity

class DataListActivity : SimpleFragmentActivity(),
    DataFragment.OnInteractionListener, DataListFragment.OnInteractionListener {

    private lateinit var master: DataListFragment

    override fun createFragment(): Fragment {
        return DataListFragment().also { master = it }
    }

    override fun getLayoutResId(): Int =  R.layout.toolbar
}
