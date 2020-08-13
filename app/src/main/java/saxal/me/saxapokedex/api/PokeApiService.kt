package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults

interface PokeApiService {
    @GET("pokemon?limit=1")
    fun listPokemon(): Call<PokedexPokemonResults>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<PokedexPokemonResults>
}