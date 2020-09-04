package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults
import saxal.me.saxapokedex.api.model.PokemonDetail

interface PokeApiService {
    @GET("pokemon")
    fun listPokemon(): Call<PokedexPokemonResults>

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") name: Int): Call<PokemonDetail>
}