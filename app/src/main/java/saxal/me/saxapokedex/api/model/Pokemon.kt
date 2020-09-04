package saxal.me.saxapokedex.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokedexPokemonResults(
    val results: List<Pokemon>
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val sprites: Sprites,
    val order: Int
) {

    val primaryType = types[0].type.name
    val secondaryType = types.getOrNull(1)?.type?.name

    // ======================
    // ui
    // ======================

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
        get() = name
            .capitalize()
//            .replace(Regex("-m"), "♂")
//            .replace(Regex("-f"), "♀")
}