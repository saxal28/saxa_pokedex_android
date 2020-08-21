package saxal.me.saxapokedex.ui.pokemondetail.pokemonstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailStatsBinding

class PokemonDetailStatsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailStatsBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }
}