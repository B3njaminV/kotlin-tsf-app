package fr.iut.tsf.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.adapater.AdaptateurContenu
import fr.iut.tsf.data.TSFDatabase
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory

class FilmListFragment : Fragment(), AdaptateurContenu.Callbacks {


    private lateinit var dataList: LiveData<List<Film>>
    private lateinit var viewModel: FilmViewModel
    private lateinit var adaptateurContenu: AdaptateurContenu
    private var listener: OnInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(FilmViewModel::class.java)
        dataList = viewModel.allMovies
        adaptateurContenu = AdaptateurContenu(dataList, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = adaptateurContenu
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        // Pour l'ajout des favoris plus tard
        //view.findViewById<FloatingActionButton>(R.id.myfav).setOnClickListener { mesFavoris() }

        dataList.observe(viewLifecycleOwner) {
            adaptateurContenu.updateList(dataList)
        }
        return view
    }

    fun updateList() {
        dataList = viewModel.allMovies
        adaptateurContenu = AdaptateurContenu(dataList, this)
    }

    override fun onStart() {
        super.onStart()
        updateList()
    }

    override fun onFilmSelect(id: Int) {
        listener?.onFilmSelect(id)
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnInteractionListener")
        }
    }

    interface OnInteractionListener {
        fun onFilmSelect(id: Int)
    }
}