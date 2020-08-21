package saxal.me.saxapokedex.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import saxal.me.saxapokedex.api.database.entity.PokemonEntity
import saxal.me.saxapokedex.api.database.entity.PokemonSpritesEntity
import saxal.me.saxapokedex.api.database.entity.PokemonStatsEntity
import saxal.me.saxapokedex.api.database.entity.PokemonTypesEntity

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
) {
    fun mapToEntity(pokemonId: Int) = PokemonTypesEntity(
        name = this.type.name,
        url = this.type.url,
        pokemonOwnerId = pokemonId.toLong()
    )
}

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
) {
    fun mapToEntity(pokemonId: Int) = PokemonStatsEntity(
        pokemonOwnerId = pokemonId.toLong(),
        base_stat = this.base_stat,
        effort = this.effort,
        name = this.stat.name,
        url = this.stat.url
    )
}

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
) {
    fun mapToEntity(pokemonId: Int) = PokemonSpritesEntity(
        pokemonOwnerId = pokemonId.toLong(),
        back_default = this.back_default,
        back_female = this.back_female,
        back_shiny = this.back_shiny,
        back_shiny_female = this.back_shiny_female,
        front_default = this.front_default,
        front_female = this.front_female,
        front_shiny = this.front_shiny,
        front_shiny_female = this.front_shiny_female,
        dream_world_default = this.other.dream_world.front_default,
        dream_world_female = this.other.dream_world.front_female,
        official_artwork_default = this.other.official_artwork.front_default,
        official_artwork_female = this.other.official_artwork.front_female
    )
}

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val stats: List<Stats>,
    val sprites: Sprites
) {
    fun mapToEntity() = PokemonEntity(
        pokemonId = this.id.toLong(),
        name = this.name
    )

    val primaryType = types[0].type.name
    val secondaryType = types.getOrNull(1)?.type?.name

    // ui

    val displayPrimaryType
        get() = primaryType.capitalize()

    val displaySecondaryType
        get() = secondaryType?.capitalize()

    val displayId: String
        get() = when (id.toString().length) {
            1 -> "#00${id}"
            2 -> "#0${id}"
            else -> "#${id}"
        }

    val displayName: String
        get() = name.capitalize()

}