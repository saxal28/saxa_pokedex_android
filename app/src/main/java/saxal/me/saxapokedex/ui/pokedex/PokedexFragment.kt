package saxal.me.saxapokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.databinding.FragmentPokedexBinding
import saxal.me.saxapokedex.util.ItemOffsetDecoration

class PokedexFragment : Fragment() {

    private lateinit var listAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val viewModel: PokedexViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokedexBinding.inflate(inflater)

        viewManager = GridLayoutManager(activity, 2)
        listAdapter = PokedexListAdapter(viewModel.data.value!!)

        val itemDecoration =
            ItemOffsetDecoration(
                requireContext(),
                R.dimen.item_offset
            )

        binding.pokedexListView.apply {
            layoutManager = viewManager
            adapter = listAdapter

            setHasFixedSize(true)

            addItemDecoration(itemDecoration)
        }


        return binding.root
    }
}
