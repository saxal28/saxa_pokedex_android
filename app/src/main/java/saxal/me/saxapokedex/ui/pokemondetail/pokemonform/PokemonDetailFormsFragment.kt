package saxal.me.saxapokedex.ui.pokemondetail.pokemonform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailFormsBinding
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailStatsBinding

class PokemonDetailFormsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailFormsBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }
}