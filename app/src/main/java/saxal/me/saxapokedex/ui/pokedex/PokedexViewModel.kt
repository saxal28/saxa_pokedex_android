package saxal.me.saxapokedex.ui.pokedex

import androidx.lifecycle.ViewModel
import saxal.me.saxapokedex.api.repository.PokedexRepository

class PokedexViewModel : ViewModel() {
    private val pokedexRepo = PokedexRepository()

//    val pokemon = pokedexRepo.allPokemon
    val pokemon = pokedexRepo.listPokemon
}