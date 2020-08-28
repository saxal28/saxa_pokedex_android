package saxal.me.saxapokedex.data

import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.constants.TypesId


class PokemonTypeResources {
    companion object {
        val data: List<PokemonTypeResource> = listOf(
            PokemonTypeResource(
                type = TypesId.FIRE,
                pokedexBackgroundResourceId = R.drawable.card_bg_fire,
                backgroundColorResourceId = R.color.fire,
                pokeballResourceId = R.drawable.card_pokeball_fire,
                tagResourceId = R.drawable.card_tag_fire
            ),
            PokemonTypeResource(
                type = TypesId.WATER,
                pokedexBackgroundResourceId = R.drawable.card_bg_water,
                backgroundColorResourceId = R.color.water,
                pokeballResourceId = R.drawable.card_pokeball_water,
                tagResourceId = R.drawable.card_tag_water
            ),
            PokemonTypeResource(
                type = TypesId.GRASS,
                pokedexBackgroundResourceId = R.drawable.card_bg_grass,
                backgroundColorResourceId = R.color.grass,
                pokeballResourceId = R.drawable.card_pokeball_grass,
                tagResourceId = R.drawable.card_tag_grass
            ),
            PokemonTypeResource(
                type = TypesId.ELECTRIC,
                pokedexBackgroundResourceId = R.drawable.card_bg_electric,
                backgroundColorResourceId = R.color.electric,
                pokeballResourceId = R.drawable.card_pokeball_electric,
                tagResourceId = R.drawable.card_tag_electric
            ),
            PokemonTypeResource(
                type = TypesId.ROCK,
                pokedexBackgroundResourceId = R.drawable.card_bg_rock,
                backgroundColorResourceId = R.color.rock,
                pokeballResourceId = R.drawable.card_pokeball_rock,
                tagResourceId = R.drawable.card_tag_rock
            ),
            PokemonTypeResource(
                type = TypesId.GROUND,
                pokedexBackgroundResourceId = R.drawable.card_bg_ground,
                backgroundColorResourceId = R.color.ground,
                pokeballResourceId = R.drawable.card_pokeball_ground,
                tagResourceId = R.drawable.card_tag_ground
            ),
            PokemonTypeResource(
                type = TypesId.POISON,
                pokedexBackgroundResourceId = R.drawable.card_bg_poison,
                backgroundColorResourceId = R.color.poison,
                pokeballResourceId = R.drawable.card_pokeball_poison,
                tagResourceId = R.drawable.card_tag_poison
            ),
            PokemonTypeResource(
                type = TypesId.GHOST,
                pokedexBackgroundResourceId = R.drawable.card_bg_ghost,
                backgroundColorResourceId = R.color.ghost,
                pokeballResourceId = R.drawable.card_pokeball_ghost,
                tagResourceId = R.drawable.card_tag_ghost
            ),
            PokemonTypeResource(
                type = TypesId.PSYCHIC,
                pokedexBackgroundResourceId = R.drawable.card_bg_psychic,
                backgroundColorResourceId = R.color.psychic,
                pokeballResourceId = R.drawable.card_pokeball_psychic,
                tagResourceId = R.drawable.card_tag_psychic
            ),
            PokemonTypeResource(
                type = TypesId.ICE,
                pokedexBackgroundResourceId = R.drawable.card_bg_ice,
                backgroundColorResourceId = R.color.ice,
                pokeballResourceId = R.drawable.card_pokeball_ice,
                tagResourceId = R.drawable.card_tag_ice
            ),
            PokemonTypeResource(
                type = TypesId.STEEL,
                pokedexBackgroundResourceId = R.drawable.card_bg_steel,
                backgroundColorResourceId = R.color.steel,
                pokeballResourceId = R.drawable.card_pokeball_steel,
                tagResourceId = R.drawable.card_tag_steel
            ),
            PokemonTypeResource(
                type = TypesId.FIGHTING,
                pokedexBackgroundResourceId = R.drawable.card_bg_fighting,
                backgroundColorResourceId = R.color.fighting,
                pokeballResourceId = R.drawable.card_pokeball_fighting,
                tagResourceId = R.drawable.card_tag_fighting
            ),

            PokemonTypeResource(
                type = TypesId.FLYING,
                pokedexBackgroundResourceId = R.drawable.card_bg_flying,
                backgroundColorResourceId = R.color.flying,
                pokeballResourceId = R.drawable.card_pokeball_flying,
                tagResourceId = R.drawable.card_tag_flying
            ),

            PokemonTypeResource(
                type = TypesId.DRAGON,
                pokedexBackgroundResourceId = R.drawable.card_bg_dragon,
                backgroundColorResourceId = R.color.dragon,
                pokeballResourceId = R.drawable.card_pokeball_dragon,
                tagResourceId = R.drawable.card_tag_dragon
            ),
            PokemonTypeResource(
                type = TypesId.FAIRY,
                pokedexBackgroundResourceId = R.drawable.card_bg_fairy,
                backgroundColorResourceId = R.color.fairy,
                pokeballResourceId = R.drawable.card_pokeball_fairy,
                tagResourceId = R.drawable.card_tag_fairy
            ),
            PokemonTypeResource(
                type = TypesId.NORMAL,
                pokedexBackgroundResourceId = R.drawable.card_bg_normal,
                backgroundColorResourceId = R.color.normal,
                pokeballResourceId = R.drawable.card_pokeball_normal,
                tagResourceId = R.drawable.card_tag_normal
            ),
            PokemonTypeResource(
                type = TypesId.BUG,
                pokedexBackgroundResourceId = R.drawable.card_bg_bug,
                backgroundColorResourceId = R.color.bug,
                pokeballResourceId = R.drawable.card_pokeball_bug,
                tagResourceId = R.drawable.card_tag_bug
            ),
            PokemonTypeResource(
                type = TypesId.UNKNOWN,
                pokedexBackgroundResourceId = R.drawable.card_bg_unknown,
                backgroundColorResourceId = R.color.unknown,
                pokeballResourceId = R.drawable.card_pokeball_unknown,
                tagResourceId = R.drawable.card_tag_unknown
            )

        )

        fun forType(type: String): PokemonTypeResource = data.firstOrNull { it.type == type } ?: data.find { it.type == TypesId.UNKNOWN }!!
    }
}

data class PokemonTypeResource(
    val type: String,
    val pokedexBackgroundResourceId: Int,
    val backgroundColorResourceId: Int,
    val pokeballResourceId: Int,
    val tagResourceId: Int
)

