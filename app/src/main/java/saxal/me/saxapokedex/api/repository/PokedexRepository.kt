package saxal.me.saxapokedex.api.repository

import android.util.Log
import androidx.lifecycle.liveData
import retrofit2.*
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.pokeService
import saxal.me.saxapokedex.constants.LoadingStatus
import java.lang.Exception

class PokedexRepository {

    private fun filterOutOtherPokemonForms(pokemon: Pokemon): Boolean = pokemon.id <= 10000
    private fun filterOutBadPokemon(pokemon: Pokemon): Boolean = pokemon.order != -1

    val listPokemon = liveData {
        emit(PokeListResult(loading = LoadingStatus.LOADING))
        try {
            val pokemon = pokeService.listPokemon().await()

            val results = pokemon.results
                .filter(::filterOutOtherPokemonForms)
                .filter(::filterOutBadPokemon)
                .sortedBy { it.order }

            emit(PokeListResult(loading = LoadingStatus.FINISHED, data = results))

        } catch (e: Exception) {
            Log.d("ERROR", "Fetch All Pokemon $e")
            emit(
                PokeListResult(
                    loading = LoadingStatus.FINISHED,
                    errorMessage = e.message ?: "Error!"
                )
            )
        }
    }

    fun getPokemonDetailsById(pokemonId: Int) = liveData {
        emit(PokeResult(loading = LoadingStatus.LOADING))

        try {
            val pokemon = pokeService.getPokemonInfo(pokemonId).await()
            emit(PokeResult(loading = LoadingStatus.FINISHED, data = pokemon))

        } catch (e: Exception) {
            Log.e("ERROR $pokemonId", "Get Pokemon Details By Id: $e")
            emit(PokeResult(errorMessage = e.message ?: "Error!"))
        }
    }

}