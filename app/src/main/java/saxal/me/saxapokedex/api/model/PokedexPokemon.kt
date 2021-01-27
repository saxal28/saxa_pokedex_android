package saxal.me.saxapokedex.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


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
data class NamedApiResource(
    val name: String,
    var url: String
) {
    fun formatName() = name.split("-").joinToString(" ") { it.capitalize() }
}

@JsonClass(generateAdapter = true)
data class Stats(
    val base_stat: Int,
    var effort: Int,
    val stat: Stat
) {
    private val maxStat = 180.00

    val statPercentage: Double
        get() = base_stat.toDouble() / maxStat

}

// Sprites
@JsonClass(generateAdapter = true)
data class SpritesOther(
    val dream_world: Sprite,
    @Json(name = "official-artwork")  // TODO: fix for developer
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
data class Ability (
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class AbilityDetail (
    val ability: Ability,
    val is_hidden: Boolean
)
