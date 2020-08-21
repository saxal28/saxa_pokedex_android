package saxal.me.saxapokedex.api.database.entity

import androidx.room.*
import saxal.me.saxapokedex.api.model.*
import java.util.*

@Entity
data class PokemonTypesEntity(
    @PrimaryKey(autoGenerate = true) val typeId: Long = 0,
    val pokemonOwnerId: Long,
    val name: String,
    var url: String
) {
    fun mapToUIModel() = Types(
        slot = 0,
        type = Type(
            name = this.name,
            url = this.url
        )
    )
}

@Entity
data class PokemonSpritesEntity(
    @PrimaryKey(autoGenerate = true) val spriteId: Long = 0,
    val pokemonOwnerId: Long,
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,
    val dream_world_default: String? = null,
    val dream_world_female: String? = null,
    val official_artwork_default: String?,
    val official_artwork_female: String?
) {
    fun mapToUIModel() = Sprites(
        back_default = this.back_default,
        back_female = this.back_female,
        back_shiny = this.back_shiny,
        back_shiny_female = this.back_shiny_female,
        front_default = this.front_default,
        front_female = this.front_female,
        front_shiny = this.front_shiny,
        front_shiny_female = this.front_shiny_female,
        other = SpritesOther(
            dream_world = Sprite(front_default = dream_world_default, front_female = dream_world_female ),
            official_artwork = Sprite(front_default = official_artwork_default, front_female = official_artwork_female)
        )
    )
}

@Entity
data class PokemonStatsEntity(
    @PrimaryKey(autoGenerate = true) val statId: Long = 0,
    val pokemonOwnerId: Long,
    val base_stat: Int,
    var effort: Int,
    val name: String,
    var url: String
) {
    fun mapToUIModel() = Stats(
        base_stat = this.base_stat,
        effort = this.effort,
        stat = Stat(name = this.name, url = this.url)
    )
}

@Entity
data class PokemonEntity(
    @PrimaryKey val pokemonId: Long,
    var modified: Long = Date().time,
    val name: String
)

@Entity
data class PokemonWithTypesEntity(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonOwnerId"
    )
    val types: List<PokemonTypesEntity>,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonOwnerId"
    )
    val stats: List<PokemonStatsEntity>,

    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "pokemonOwnerId"
    )
    val sprites: PokemonSpritesEntity

) {
    fun mapToUIModel() = Pokemon(
        id = this.pokemon.pokemonId.toInt(),
        name = this.pokemon.name,
        sprites = this.sprites.mapToUIModel(),
        types = this.types.map { it.mapToUIModel() },
        stats = this.stats.map { it.mapToUIModel() }
    )
}
