package fr.iut.tsf.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.tsf.R
import fr.iut.tsf.adapater.AdaptateurContenu
import fr.iut.tsf.data.TSFDatabase

class DataListFragment : Fragment(), AdaptateurContenu.Callbacks {

    private var dataList = TSFDatabase.getInstance().TSFDao().getAll()
    private var adaptateurContenu = AdaptateurContenu(dataList.asLiveData(), this)
    private var listener: OnInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = adaptateurContenu
        view.findViewById<FloatingActionButton>(R.id.myfav).setOnClickListener { mesFavoris() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    fun updateList() {
        dataList = TSFDatabase.getInstance().TSFDao().getAll()
        adaptateurContenu.updateList(dataList.asLiveData());
    }


    private fun mesFavoris() {
        listener?.mesFavoris()
    }


    override fun onFilmSelect(id : Int) {
        listener?.onFilmSelect(id)
    }

    interface OnInteractionListener {
        fun onFilmSelect(id: Int)
        fun mesFavoris()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}