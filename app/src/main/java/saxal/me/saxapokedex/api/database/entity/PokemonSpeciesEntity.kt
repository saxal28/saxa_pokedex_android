package saxal.me.saxapokedex.api.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import saxal.me.saxapokedex.api.model.*
import java.util.*

@Entity
data class EggGroupEntity(
    @PrimaryKey(autoGenerate = true) val eggGroupId: Long = 0,
    val pokemonOwnerId: Long,
    val name: String,
    var url: String
) {
    fun mapToUIModel() = EggGroup(name, url)
}

@Entity
data class FlavorTextEntryEntity(
    @PrimaryKey(autoGenerate = true) val flavorTextEntryId: Long = 0,
    val pokemonOwnerId: Long,

    val flavor_text: String,
    val language: String,
    val languageUrl: String,

    val version: String,
    val versionUrl: String

) {
    fun mapToUIModel() = FlavorTextEntries(
        flavor_text = this.flavor_text,
        language = Language(
            name = language,
            url = languageUrl
        ),
        version = Version(
            name = version,
            url = versionUrl
        )
    )
}

@Entity
data class PokemonSpecieEntity(
    @PrimaryKey var pokemonId: Long,
    var modified: Long = Date().time,

    val base_happiness: Int,
    val capture_rate: Int,
    val gender_rate: Int,
    val hatch_counter: Int
)

@Entity
data class PokemonSpeciesEntity(
    @Embedded val pokemonSpecies: PokemonSpecieEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonOwnerId"
    )
    val egg_groups: List<EggGroupEntity>,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonOwnerId"
    )
    val flavor_text_entries: List<FlavorTextEntryEntity>

) {
    fun mapToUIModel() = PokemonSpecies(
        base_happiness = pokemonSpecies.base_happiness,
        capture_rate = pokemonSpecies.capture_rate,
        gender_rate = pokemonSpecies.gender_rate,
        hatch_counter = pokemonSpecies.hatch_counter,
        egg_groups = this.egg_groups.map { EggGroup(it.name, it.url) },
        flavor_text_entries = this.flavor_text_entries.map {
            FlavorTextEntries(
                flavor_text = it.flavor_text,
                language = Language(
                    name = it.language,
                    url = it.languageUrl
                ),
                version = Version(
                    name = it.version,
                    url = it.versionUrl
                )
            )
        }
    )
}