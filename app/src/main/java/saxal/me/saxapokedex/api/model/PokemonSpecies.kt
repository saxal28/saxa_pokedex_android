package saxal.me.saxapokedex.api.model
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EggGroup(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Language(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Version(
    val name: String,
    val url: String
)



@JsonClass(generateAdapter = true)
data class FlavorTextEntries(
    val flavor_text: String,
    val language: Language,
    val version: Version
)

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    val base_happiness: Int,
    val capture_rate: Int,
    val egg_groups: List<EggGroup>,
    val flavor_text_entries: List<FlavorTextEntries>,
    val gender_rate: Int,
    val hatch_counter: Int
) {

    private val IS_SEXLESS = -1

    // gender_rate is based on an eighth (1/8 === 12.5% Female)
    private val baseGender = 8.00
    val femalePercentage = if (gender_rate == IS_SEXLESS) "--" else {
        "${(gender_rate.toDouble() / baseGender) * 100}%"
    }

    val malePercentage = if (gender_rate == IS_SEXLESS) "--" else {
        "${((baseGender - gender_rate) / baseGender) * 100}%"
    }


    val defaultFlavorText = flavor_text_entries
        .last { it.language.name == "en" }.flavor_text
        .replace(Regex("\n"), " ")

    val displayEggGroup = egg_groups.joinToString(", ") { it.name.capitalize() }
}