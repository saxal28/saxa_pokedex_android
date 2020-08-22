package saxal.me.saxapokedex.ui.pokemondetail

import androidx.lifecycle.*
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.repository.PokeResult
import saxal.me.saxapokedex.api.repository.PokedexRepository

class PokemonDetailViewModel: ViewModel() {

    private val pokemonRepository = PokedexRepository()

    val pokemonId: MutableLiveData<Int?> = MutableLiveData()
    val pokemon: MutableLiveData<Pokemon?> = MutableLiveData()

    val loadPokemonById = pokemonId.switchMap { pokemonId ->
        if(pokemonId != null) {
            if(pokemon.value == null){
                // if no pokemon in view-model, fetch
                pokemonRepository.getPokemonById(pokemonId)
            } else {
                MutableLiveData(PokeResult(data = pokemon.value!!))
            }
        } else {
           MutableLiveData(PokeResult())
        }
    }
}
