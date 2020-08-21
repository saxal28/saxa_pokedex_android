package saxal.me.saxapokedex.ui.pokemondetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import saxal.me.saxapokedex.ui.pokemondetail.pokemonabout.PokemonDetailAboutFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonform.PokemonDetailFormsFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves.PokemonDetailMovesFragment
import saxal.me.saxapokedex.ui.pokemondetail.pokemonstats.PokemonDetailStatsFragment


class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    private var currentFragment: Fragment? = null

    private val aboutFragment by lazy { PokemonDetailAboutFragment() }
    private val statsFragment by lazy { PokemonDetailStatsFragment() }
    private val formsFragment by lazy { PokemonDetailFormsFragment() }
    private val movesFragment by lazy { PokemonDetailMovesFragment() }

    override fun createFragment(position: Int): Fragment {
        val frag = when(position) {
            0 -> aboutFragment
            1 -> statsFragment
            2 -> formsFragment
            else -> movesFragment
        }

        currentFragment = frag

        return frag
    }

}