package saxal.me.saxapokedex.ui.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.constants.LoadingStatus
import saxal.me.saxapokedex.databinding.FragmentPokedexBinding
import saxal.me.saxapokedex.ui.pokemondetail.PokemonDetailFragment
import saxal.me.saxapokedex.util.ItemOffsetDecoration
import saxal.me.saxapokedex.util.hideKeyboard

class PokedexFragment : Fragment() {

    private lateinit var listAdapter: PokedexListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentPokedexBinding

    private val viewModel: PokedexViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokedexBinding.inflate(inflater)

        viewManager = GridLayoutManager(activity, 2)
        listAdapter = PokedexListAdapter(viewModel.pokemonResult.value?.data ?: listOf())

        val itemDecoration =
            ItemOffsetDecoration(
                requireContext(),
                R.dimen.item_offset
            )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.pokedexListView.apply {
            layoutManager = viewManager
            adapter = listAdapter

            itemAnimator = DefaultItemAnimator()

            setHasFixedSize(true)

            addItemDecoration(itemDecoration)
        }

        viewModel.pokemonResult.observe(viewLifecycleOwner, Observer {
            Log.e("STATUS", it.loading)

            binding.loader.visibility =  when(it.loading) {
                LoadingStatus.LOADING -> View.VISIBLE
                LoadingStatus.FINISHED -> View.GONE
                else -> View.GONE
            }

            viewModel.allPokemon.value = it.data
            listAdapter.updateData(it.data)

            binding.emptyListView.visibility = if(it.data.isEmpty()) View.VISIBLE else View.GONE

            // TODO: handle filters here
            viewModel.searchText.value = "charmander"
        })

        listAdapter.pokemonToNavigateTo.observe(viewLifecycleOwner, Observer { pokemon ->
            if(pokemon != null) {
                PokemonDetailFragment
                    .newInstance(pokemon)
                    .show(childFragmentManager, PokemonDetailFragment.TAG)

                listAdapter.clearActivePokemonId()
            }
        })

        // ==================
        // search
        // ==================

        viewModel.searchPokemon.observe(viewLifecycleOwner, Observer {
            listAdapter.updateData(it)
            binding.emptyListView.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        })

        viewModel.searchText.observe(viewLifecycleOwner, Observer {
            viewModel.searchPokemon()
            binding.customInputButtonRight.isVisible = it.isNotBlank()
        })

        binding.customInputButtonRight.setOnClickListener { viewModel.searchText.value = "" }

        // hide keyboard on scroll

        binding.pokedexListView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        binding.pokedexListView.hideKeyboard()
                    }
                }
            }
        )

        return binding.root
    }
}
