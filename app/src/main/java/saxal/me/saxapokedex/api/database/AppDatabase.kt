package saxal.me.saxapokedex.api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import saxal.me.saxapokedex.api.database.dao.PokemonDao
import saxal.me.saxapokedex.api.database.entity.*


@Database(entities = [
    PokemonEntity::class,
    PokemonTypesEntity::class,
    PokemonStatsEntity::class,
    PokemonSpritesEntity::class,
    PokemonAbilitiesEntity::class,
    PokemonSpecieEntity::class,
    FlavorTextEntryEntity::class,
    EggGroupEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}

// Singleton
class Database {
    companion object {
        var instance: AppDatabase? = null

        fun initDatabase(context: Context){
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "saxa-pokedex"
            ).build()
        }
    }
}