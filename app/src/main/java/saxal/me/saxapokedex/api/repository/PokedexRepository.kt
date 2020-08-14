package saxal.me.saxapokedex.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import retrofit2.*
import saxal.me.saxapokedex.api.model.PokedexPokemon
import saxal.me.saxapokedex.api.pokeService
import saxal.me.saxapokedex.api.model.PokedexPokemonResults
import saxal.me.saxapokedex.api.model.Pokemon
import java.lang.Exception

class PokedexRepository {

    private fun getIdFromUrl(url: String): String? {
        val idRegex = Regex("/\\d+/")
        val slashRegex = Regex("/")

        return idRegex.find(url)?.value?.replace(slashRegex, "")
    }

    private fun getPokedexImage(url: String): String {
        val id = getIdFromUrl(url)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }


    // TODO: coroutine + live data
    val allPokemon: LiveData<List<Pokemon>> = liveData {

        emit(emptyList())

        try {
            var list: List<Pokemon> = listOf()
            val pokemon = pokeService.listPokemon().await()

            pokemon.results.map { pokedexPokemon ->
                val pokemonDetail = pokeService.getPokemonInfo(pokedexPokemon.name).await()
                list = list.plus(pokemonDetail)
            }

            emit(list)

        } catch (exception: Exception) {
            Log.e("Exception", "$exception")
        }
    }


    // TODO: create cool wrapper
    fun getPokedexPokemon(): LiveData<List<PokedexPokemon>> {
        val data: MutableLiveData<List<PokedexPokemon>> = MutableLiveData(emptyList())

        pokeService.listPokemon().enqueue(object : Callback<PokedexPokemonResults> {
            override fun onResponse(
                call: Call<PokedexPokemonResults>,
                response: Response<PokedexPokemonResults>
            ) {
                val body = response.body()?.results ?: emptyList()

                data.value = body.map {
                    it.apply {
                        this.url = getPokedexImage(this.url)

                    }
                }

            }

            override fun onFailure(call: Call<PokedexPokemonResults>, t: Throwable) {
                Log.i("ERROR", t.localizedMessage ?: "error")
            }

        })

        return data
    }
}