package saxal.me.saxapokedex.api.model

import com.squareup.moshi.JsonClass
import saxal.me.saxapokedex.util.roundTo
import kotlin.math.roundToInt

@JsonClass(generateAdapter = true)
data class EvolutionDetails(
    val gender: Int? = null,
    val held_item: NamedApiResource? = null,
    val item: NamedApiResource? = null,
    val known_move: NamedApiResource? = null,
    val known_move_type: NamedApiResource? = null,
    val location: NamedApiResource? = null,
    val min_affection: Int? = null,
    val min_beauty: Int? = null,
    val min_happiness:  Int? = null,
    val min_level: Int? = null,
    val needs_overworld_rain: Boolean? = null,
    val party_species:  NamedApiResource? = null,
    val party_type:  NamedApiResource? = null,
    val relative_physical_stats:  Int? = null,
    val time_of_day:  String? = null,
    val trade_species:  NamedApiResource? = null,
    val trigger: NamedApiResource? = null,
    val turn_upside_down: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class EvolutionChain(
    val evolves_to: List<EvolutionChain>,
    val evolution_details: List<EvolutionDetails>,
    val is_baby: Boolean,
    val species: NamedApiResource
)

@JsonClass(generateAdapter = true)
data class EvolutionChainFetched(
    val id: Int,
    val chain: EvolutionChain
)

@JsonClass(generateAdapter = true)
data class GameIndex(
    val game_index: Int,
    val version: NamedApiResource
)

@JsonClass(generateAdapter = true)
data class PokemonDetail(
    val name: String,
    val id: Int,
    val order: Int,
    val height: Int,
    val weight: Int,
    val base_experience: Int,
    val base_happiness: Int,
    val capture_rate: Int,

    val sprites: Sprites,
    val types: List<Types>,
    val stats: List<Stats>,

    val gender_rate: Int,
    val hatch_counter: Int,
    val flavor_text_entries: List<FlavorTextEntries>,
    val egg_groups: List<EggGroup>,

    val abilities: List<AbilityDetail>,
    val evolution_chain_fetched: EvolutionChainFetched,

    // TODO: game indexes
    val game_indices: List<GameIndex>
) {

    val primaryType = types[0].type.name
    val secondaryType = types.getOrNull(1)?.type?.name

    val hpStat = stats.find { it.stat.name == "hp" }
    val attackStat = stats.find { it.stat.name == "attack" }
    val defenseStat = stats.find { it.stat.name == "defense" }
    val specialAttackStat = stats.find { it.stat.name == "special-attack" }
    val specialDefenseStat = stats.find { it.stat.name == "special-defense" }
    val speedStat = stats.find { it.stat.name == "speed" }

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
        get() = name
            .capitalize()
//            .replace(Regex("-m"), "♂")
//            .replace(Regex("-f"), "♀")

    // weight is in hectograms
    val displayWeight: String
        get() {
            val lbs = (weight.toDouble() / 4.5359237).roundTo(1)// there is 4.5 hecto per lbs
            val kg = (weight.toDouble() / 10.0).roundTo(1) // 1kg === 10 hecto

            return "$lbs lbs (${kg}kg)"
        }

    // height is in decimeters
    val displayHeight: String
        get() {
            val totalInches = (height.toDouble() * 3.93701).roundTo(2)
            val feet = (totalInches / 12).roundToInt()
            val centimeters = (totalInches / 2.54).roundTo(2)
            val inches = (totalInches % 12).roundToInt().toString()
            val inchesDisplay = if(inches.length == 1) "0$inches" else inches

            return "${feet}\' ${inchesDisplay}\" ($centimeters cm)"
        }

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