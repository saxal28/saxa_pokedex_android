package saxal.me.saxapokedex.api

import retrofit2.Call
import retrofit2.http.*
import saxal.me.saxapokedex.api.model.PokedexPokemonResults
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.api.model.PokemonSpecies

interface PokeApiService {
    // ?limit=151
    @GET("pokemon")
    fun listPokemon(): Call<PokedexPokemonResults>

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") name: Int): Call<PokemonDetail>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<PokemonSpecies>

    @GET("pokemon/{name}")
    fun getPokemonDetails(@Path("name") name: String): Call<Pokemon>
}