package saxal.me.saxapokedex.ui.pokemondetail.pokemonabout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import saxal.me.saxapokedex.api.model.PokemonSpecies
import saxal.me.saxapokedex.constants.LoadingStatus
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

        sharedViewModel.loadPokemonSpecieDetailsById.observe(viewLifecycleOwner, Observer {

            binding.loader.visibility =  when(it.loading) {
                LoadingStatus.LOADING -> View.VISIBLE
                LoadingStatus.FINISHED -> View.GONE
                else -> View.GONE
            }

            if(it.data != null) {
                setupUI(it.data)
            }

            if(it.errorMessage != null) {
                Log.i("ABOUT | ERROR", it.errorMessage!!)
            }
        })

        return binding.root
    }

    private fun setupUI(pokemonSpecieData: PokemonSpecies) {
        binding.description = pokemonSpecieData.defaultFlavorText
        binding.percentFemale = pokemonSpecieData.femalePercentage
        binding.percentMale = pokemonSpecieData.malePercentage

        binding.height = sharedViewModel.pokemon.value?.height?.toString() ?: "0"
        binding.weight = sharedViewModel.pokemon.value?.displayWeight
    }
}