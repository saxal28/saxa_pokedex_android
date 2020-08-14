package saxal.me.saxapokedex.ui.pokedex

import androidx.lifecycle.ViewModel
import saxal.me.saxapokedex.api.repository.PokedexRepository

class PokedexViewModel : ViewModel() {

    val pokedexRepo = PokedexRepository()

    val pokemon = pokedexRepo.allPokemon
}