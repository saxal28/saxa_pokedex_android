package saxal.me.saxapokedex.ui.pokemondetail.pokemonabout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailAboutBinding

class PokemonDetailAboutFragment: Fragment() {

    lateinit var binding: FragmentPokemonDetailAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailAboutBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }
}