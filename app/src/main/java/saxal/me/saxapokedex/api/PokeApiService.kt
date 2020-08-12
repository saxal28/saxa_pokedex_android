package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults

interface PokeApiService {
    @GET("pokemon?limit=50")
    fun listPokemon(): Call<PokedexPokemonResults>
}