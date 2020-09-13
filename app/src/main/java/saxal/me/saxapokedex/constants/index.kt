package saxal.me.saxapokedex.constants

class TypesId {
    companion object {
        const val FIRE = "fire"
        const val WATER = "water"
        const val GRASS = "grass"
        const val ELECTRIC = "electric"
        const val ROCK = "rock"
        const val GROUND = "ground"
        const val POISON = "poison"
        const val GHOST = "ghost"
        const val PSYCHIC = "psychic"
        const val ICE = "ice"
        const val STEEL = "steel"
        const val FIGHTING = "fighting"
        const val FLYING = "flying"
        const val DRAGON = "dragon"
        const val FAIRY = "fairy"
        const val NORMAL = "normal"
        const val BUG = "bug"
        const val UNKNOWN = "unknown"
    }
}

class LoadingStatus {
    companion object {
        const val NOT_STARTED = "not_started"
        const val LOADING = "loading"
        const val FINISHED = "finished"
    }
}

class Version {
    companion object {
        const val RED = "red"
        const val BLUE = "blue"
        const val YELLOW = "yellow"

        const val GOLD = "gold"
        const val SILVER = "silver"
        const val CRYSTAL = "crystal"

        const val RUBY = "ruby"
        const val SAPPHIRE = "sapphire"
        const val EMERALD = "emerald"

        const val FIRE_RED = "firered"
        const val FIRE_BLUE = "leafgreen"

        const val DIAMOND = "diamond"
        const val PEARL = "pearl"
        const val PLATINUM = "platinum"

        const val HEART_GOLD = "heartgold"
        const val SOUL_SILVER = "soulsilver"

        const val BLACK = "black"
        const val WHITE = "white"
        const val BLACK_2 = "black-2"
        const val WHITE_2 = "white-2"

        val ALL = listOf(
            RED, BLUE, YELLOW,
            GOLD, SILVER, CRYSTAL,
            RUBY, SAPPHIRE, EMERALD,
            FIRE_RED, FIRE_BLUE,
            DIAMOND, PEARL, PLATINUM,
            HEART_GOLD, SOUL_SILVER,
            BLACK, WHITE, BLACK_2, WHITE_2
        )
    }
}