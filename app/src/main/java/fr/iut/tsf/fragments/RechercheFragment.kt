package fr.iut.tsf.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iut.tsf.R
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.adapater.AdaptateurRecherche
import fr.iut.tsf.model.Film
import fr.iut.tsf.viewmodel.FilmViewModel
import fr.iut.tsf.viewmodel.FilmViewModelFactory
import kotlinx.coroutines.Dispatchers

class RechercheFragment : Fragment(), AdaptateurRecherche.Callbacks {

    private lateinit var dataList: LiveData<List<Film>>
    private lateinit var viewModel: FilmViewModel
    private lateinit var adaptateurContenu: AdaptateurRecherche
    private var listener: OnInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = FilmViewModelFactory((requireActivity().application as TSFApplication).repository).create(FilmViewModel::class.java)
        dataList = viewModel.search("Avengers")
        adaptateurContenu = AdaptateurRecherche(this)

        val view = inflater.inflate(R.layout.fragment_recherche, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        rv.adapter = adaptateurContenu
        rv.layoutManager = GridLayoutManager(requireContext(), 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList.observe(viewLifecycleOwner) {
            adaptateurContenu.submitList(dataList.value)
        }
        val search = view.findViewById<EditText>(R.id.searchText)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                lifecycleScope.launchWhenResumed {
                    dataList = viewModel.search(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onStart() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.recherche)
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