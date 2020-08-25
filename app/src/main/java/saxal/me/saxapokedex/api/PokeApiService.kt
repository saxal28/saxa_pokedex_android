package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.model.PokemonSpecies

interface PokeApiService {
    @GET("pokemon?limit=151")
    fun listPokemon(): Call<PokedexPokemonResults>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<Pokemon>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<PokemonSpecies>
}