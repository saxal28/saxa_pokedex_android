package saxal.me.saxapokedex.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.*
import retrofit2.*
import saxal.me.saxapokedex.api.CacheService
import saxal.me.saxapokedex.api.model.JsonPokemon
import saxal.me.saxapokedex.api.pokeService
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.constants.LoadingStatus
import saxal.me.saxapokedex.util.Timestamp
import java.lang.Exception
import java.util.concurrent.ConcurrentLinkedQueue


class PokedexRepository {

    // TODO: making design decision to fetch generated JSON pokemon
    val listPokemon = liveData {
        emit(PokeListResult(loading = LoadingStatus.LOADING))
        try {
            val pokemon = pokeService.listPokemon().await()
            Log.i("MockJsonInterceptor", pokemon.results.toString())
            emit(PokeListResult(loading = LoadingStatus.FINISHED, data = pokemon.results))
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
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
            Log.e("GET", "e: $e")
            emit(PokeResult(errorMessage = e.message ?: "Error!"))
        }
    }


    // coroutine + live data
//    val allPokemon: LiveData<PokeListResult<Pokemon>> = liveData {
//
//        emit(PokeListResult(loading = LoadingStatus.LOADING))
//        val startTime = Timestamp.time()
//
//        val cachedPokemon = CacheService.loadPokemon()
//
//        if(cachedPokemon.isNotEmpty()) {
//            // use cached pokemon
//            emit(PokeListResult(loading = LoadingStatus.FINISHED, data = cachedPokemon))
//        } else {
//            try {
//                var list: List<Pokemon>
//
//                val pokemon = pokeService.listPokemon().await()
//                val lstOfReturnData = ConcurrentLinkedQueue<Response<Pokemon>>()
//
//                coroutineScope {
//                    pokemon.results.map { it.name }.forEach { name ->
//                        launch(Dispatchers.IO) {
//                            lstOfReturnData.add(pokeService.getPokemonInfo(name).execute())
//                        }
//                    }
//                }
//
//                list = lstOfReturnData.map { it.body()!! }.sortedBy { it.id }
//
//                CacheService.savePokemon(list)
//
//                val endTime = Timestamp.time()
//                Timestamp.getTimestampDifference(startTime, endTime)
//
//
//
//                emit(
//                    PokeListResult(
//                        loading = LoadingStatus.FINISHED,
//                        data = list
//                    )
//                )
//
//            } catch (exception: Exception) {
//                Log.e("Exception", "$exception")
//                emit(
//                    PokeListResult(
//                        loading = LoadingStatus.FINISHED,
//                        errorMessage = exception.message ?: "Error!"
//                    )
//                )
//            }
//        }
//    }

    // GET / Pokemon by Id
    fun getPokemonById(pokemonId: Int): LiveData<PokeResult<Pokemon>> = liveData {
        emit(PokeResult(loading = LoadingStatus.LOADING))

        try {
            val pokemon = CacheService.getPokemonById(pokemonId)
            emit(PokeResult(loading = LoadingStatus.FINISHED, data = pokemon))

        } catch (e: Exception) {
            Log.e("GET", "e: $e")
            emit(PokeResult(errorMessage = e.message ?: "Error!"))
        }
    }

    // GET POKEMON SPECIES DETAILS
    fun getPokemonSpecieDetails(pokemonId: Int) = liveData {
        try {

            emit(PokeResult(loading = LoadingStatus.LOADING))

            // Find cached version, if exists and emit
            val foundPokemonSpecies = CacheService.getPokemonSpecies(pokemonId)

            if (foundPokemonSpecies != null) {
                Log.i("SPECIES", "loading from disk")
                emit(PokeResult(loading = LoadingStatus.FINISHED, data = foundPokemonSpecies))
            } else {
                Log.i("SPECIES", "loading from api and saving")
                val details = pokeService.getPokemonSpecies(pokemonId).await()
                CacheService.savePokemonSpecies(details.mapToEntity(pokemonId))
                emit(PokeResult(loading = LoadingStatus.FINISHED, data = details))
            }

        } catch (e: Exception) {
            Log.e("GET", "e: $e")
            emit(PokeResult(errorMessage = e.message ?: "Error!"))
        }
    }

}