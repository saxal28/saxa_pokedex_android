package saxal.me.saxapokedex.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokedexPokemonResults(
    val results: List<PokedexPokemon>
)

@JsonClass(generateAdapter = true)
data class PokedexPokemon(
    val name: String,
    var url: String
)