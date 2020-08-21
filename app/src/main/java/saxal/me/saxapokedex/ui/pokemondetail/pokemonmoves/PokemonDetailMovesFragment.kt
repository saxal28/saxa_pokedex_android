package saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailMovesBinding

class PokemonDetailMovesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailMovesBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }
}