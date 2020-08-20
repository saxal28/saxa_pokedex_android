package saxal.me.saxapokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment: Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))

        setupUI(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentPokemonDetailBinding) {
        binding.pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/147.png"
        binding.pokemonName = "Dratini"
        binding.pokemonType = "Dragon"
        binding.pokemonType2 = "Ice"
        binding.pokemonId = "#147"
        binding.pokemonProfileView.setBackgroundColor(resources.getColor(R.color.dragon))

        binding.pokemonTypeView.setBackgroundResource(R.drawable.card_tag_dragon)
        binding.pokemonType2View.setBackgroundResource(R.drawable.card_tag_dragon)

        binding.pokeballView.setImageResource(R.drawable.card_pokeball_dragon)

    }
}