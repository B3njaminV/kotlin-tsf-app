package fr.iut.tsf.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(FilmViewModel::class.java)
        dataList = viewModel.allMovies
        adaptateurContenu = AdaptateurContenu(this)

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = adaptateurContenu
        rv.layoutManager = GridLayoutManager(requireContext(), 3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_activity)
        toolbar?.title = "Films Populaires"
        dataList.observe(viewLifecycleOwner) {
            adaptateurContenu.submitList(dataList.value)
        }
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onFilmSelect(id: Int) {
        listener?.onFilmSelect(id)
    }

    override fun onResume() {
        super.onResume()
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