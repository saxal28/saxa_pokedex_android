package saxal.me.saxapokedex.api.model
import com.squareup.moshi.JsonClass
import saxal.me.saxapokedex.api.database.entity.EggGroupEntity
import saxal.me.saxapokedex.api.database.entity.FlavorTextEntryEntity
import saxal.me.saxapokedex.api.database.entity.PokemonSpecieEntity
import saxal.me.saxapokedex.api.database.entity.PokemonSpeciesEntity

@JsonClass(generateAdapter = true)
data class EggGroup(
    val name: String,
    val url: String
) {
    fun mapToEntity(pokemonId: Int) = EggGroupEntity(
        name = name,
        url = url,
        pokemonOwnerId = pokemonId.toLong()
    )
}

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
) {
    fun mapToEntity(pokemonId: Int) = FlavorTextEntryEntity(
       flavor_text = flavor_text,
        language = language.name,
        languageUrl = language.url,
        version = version.name,
        versionUrl = version.url,
        pokemonOwnerId = pokemonId.toLong()
    )
}

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    val base_happiness: Int,
    val capture_rate: Int,
    val gender_rate: Int,
    val hatch_counter: Int,
    val egg_groups: List<EggGroup>,
    val flavor_text_entries: List<FlavorTextEntries>
) {

    fun mapToEntity(pokemonId: Int) = PokemonSpeciesEntity(
        pokemonSpecies = PokemonSpecieEntity(
            base_happiness = base_happiness,
            capture_rate = capture_rate,
            gender_rate = gender_rate,
            hatch_counter = hatch_counter,
            pokemonId = pokemonId.toLong()
        ),
        egg_groups = egg_groups.map { it.mapToEntity(pokemonId) },
        flavor_text_entries = flavor_text_entries.map { it.mapToEntity(pokemonId) }
    )

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