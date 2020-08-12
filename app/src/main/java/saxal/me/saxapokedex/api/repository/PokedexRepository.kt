package saxal.me.saxapokedex.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import saxal.me.saxapokedex.api.model.PokedexPokemon
import saxal.me.saxapokedex.api.pokeService
import retrofit2.Callback
import retrofit2.Response
import saxal.me.saxapokedex.api.model.PokedexPokemonResults

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