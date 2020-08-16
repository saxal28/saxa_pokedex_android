package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults
import saxal.me.saxapokedex.api.model.Pokemon

interface PokeApiService {
    @GET("pokemon?limit=8")
    fun listPokemon(): Call<PokedexPokemonResults>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<Pokemon>
}