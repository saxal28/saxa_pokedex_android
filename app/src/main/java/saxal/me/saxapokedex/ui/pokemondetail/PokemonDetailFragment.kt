package saxal.me.saxapokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.data.PokemonTypeResource
import saxal.me.saxapokedex.data.PokemonTypeResources
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : DialogFragment() {

    companion object {
        const val POKEMON_ID = "pokemon"
        const val POKEMON_TYPE = "pokemon_type"
        const val TAG = "POKEMON_DETAILS"

        fun newInstance(pokemon: Pokemon): PokemonDetailFragment {
            return PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(POKEMON_ID, pokemon.id)
                    putString(POKEMON_TYPE, pokemon.primaryType)
                }
            }
        }
    }

    // shared viewmodel
    private val sharedViewModel: PokemonDetailViewModel by activityViewModels()

    lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var adapter: DemoCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))

        arguments?.let { args ->
            sharedViewModel.pokemonId.value = args.getInt(POKEMON_ID)
            setupPokemonDetailsUI(binding, args.getString(POKEMON_TYPE)!!)
        }

        sharedViewModel.loadPokemonById.observe(viewLifecycleOwner, Observer { pokeResult ->
            if (pokeResult.data != null) {
                sharedViewModel.pokemon.value = pokeResult.data
                setupText(binding, pokeResult.data)
            }
        })

        // setup back button
        binding.pokemonNavbar.backButton.setOnClickListener {
            dismiss()
        }

        // set up pager
        adapter = DemoCollectionAdapter(this, sharedViewModel.pokemonId.value!!)
        binding.pokemonDetailPager.adapter = adapter

        binding.pokemonDetailPager.setCurrentItem(0, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(binding.tabLayout, binding.pokemonDetailPager) { tab, position ->
            tab.text = when ((position + 1).toString()) {
                "1" -> "About"
                "2" -> "Stats"
                "3" -> "Form"
                "4" -> "Moves"
                else -> "Tab ${position + 1}"
            }
        }.attach()
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    private fun setupUIForType(
        binding: FragmentPokemonDetailBinding,
        resource: PokemonTypeResource
    ) {
        val color = resources.getColor(resource.backgroundColorResourceId)

        binding.pokemonProfileView.setBackgroundColor(color)
        binding.pokemonNavbar.setBackgroundColor(color)
        binding.pokeballView.setImageResource(resource.pokeballResourceId)
        binding.pokemonTypeView.setBackgroundResource(resource.tagResourceId)
        binding.pokemonType2View.setBackgroundResource(resource.tagResourceId)
    }

    private fun setupText(binding: FragmentPokemonDetailBinding, pokemon: Pokemon) {
        binding.pokemonImageUrl = pokemon.sprites.other.official_artwork.front_default
        binding.pokemonName = pokemon.displayName
        binding.pokemonType = pokemon.displayPrimaryType
        binding.pokemonType2 = pokemon.displaySecondaryType
        binding.pokemonId = pokemon.displayId
    }

    private fun setupPokemonDetailsUI(binding: FragmentPokemonDetailBinding, type: String) {
        val resource = PokemonTypeResources.forType(type)
        setupUIForType(binding, resource)

    }

}