package saxal.me.saxapokedex.ui.pokemondetail

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import saxal.me.saxapokedex.ui.pokemondetail.pokemonabout.PokemonDetailAboutFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonform.PokemonDetailFormsFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves.PokemonDetailMovesFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonstats.PokemonDetailStatsFragment


class DemoCollectionAdapter(fragment: Fragment, pokemonId: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragments.count()

    var fragments = listOf(
        PokemonDetailAboutFragment(),
        PokemonDetailStatsFragment(),
        PokemonDetailFormsFragment(),
        PokemonDetailMovesFragment()
    )

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}