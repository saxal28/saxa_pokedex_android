package saxal.me.saxapokedex.ui.pokemondetail

import androidx.lifecycle.*
import saxal.me.saxapokedex.api.model.Moves
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.api.repository.PokeResult
import saxal.me.saxapokedex.api.repository.PokedexRepository

class PokemonDetailViewModel : ViewModel() {

    private val pokemonRepository = PokedexRepository()

    val pokemonId: MutableLiveData<Int?> = MutableLiveData()
    val pokemon: MutableLiveData<PokemonDetail?> = MutableLiveData()

    val loadPokemonDetails = pokemonId.switchMap {
        if (it != null) {
            pokemonRepository.getPokemonDetailsById(it)
        } else {
            MutableLiveData(PokeResult())
        }
    }
}
