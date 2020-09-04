package saxal.me.saxapokedex.ui.pokemondetail.pokemonabout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailAboutBinding
import saxal.me.saxapokedex.ui.pokemondetail.PokemonDetailViewModel

class PokemonDetailAboutFragment: Fragment() {

    lateinit var binding: FragmentPokemonDetailAboutBinding

    private val sharedViewModel: PokemonDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailAboutBinding.inflate(LayoutInflater.from(context))

        sharedViewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon ->
            if(pokemon != null) {
                setupUI(pokemon)
            }
        })

        return binding.root
    }

    private fun setupUI(pokemon: PokemonDetail) {
        binding.description = pokemon.defaultFlavorText
        binding.percentFemale = pokemon.femalePercentage
        binding.percentMale = pokemon.malePercentage

        binding.height = sharedViewModel.pokemon.value?.displayHeight
        binding.weight = sharedViewModel.pokemon.value?.displayWeight
        binding.eggGroup = pokemon.displayEggGroup
    }
}