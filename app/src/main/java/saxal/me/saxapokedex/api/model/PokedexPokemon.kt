package saxal.me.saxapokedex.api.model

import com.squareup.moshi.Json
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


// Types:
@JsonClass(generateAdapter = true)
data class Type(
    val name: String,
    var url: String
)

@JsonClass(generateAdapter = true)
data class Types(
    val slot: Int,
    var type: Type
)

// Stats
@JsonClass(generateAdapter = true)
data class Stat(
    val name: String,
    var url: String
)

@JsonClass(generateAdapter = true)
data class Stats(
    val base_stat: Int,
    var effort: Int,
    val stat: Stat
)

// Sprites
@JsonClass(generateAdapter = true)
data class SpritesOther(
    val dream_world: Sprite,
    // TODO: fix for developer
    @Json(name = "official-artwork")
    val official_artwork: Sprite
)

@JsonClass(generateAdapter = true)
data class Sprite(
    val front_default: String? = null,
    val front_female: String? = null
)

@JsonClass(generateAdapter = true)
data class Sprites(
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,
    val other: SpritesOther
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val stats: List<Stats>,
    val sprites: Sprites
)