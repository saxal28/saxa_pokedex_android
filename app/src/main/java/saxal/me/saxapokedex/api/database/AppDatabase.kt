package saxal.me.saxapokedex.api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import saxal.me.saxapokedex.api.database.dao.PokemonDao
import saxal.me.saxapokedex.api.database.entity.PokemonEntity
import saxal.me.saxapokedex.api.database.entity.PokemonSpritesEntity
import saxal.me.saxapokedex.api.database.entity.PokemonStatsEntity
import saxal.me.saxapokedex.api.database.entity.PokemonTypesEntity


@Database(entities = [
    PokemonEntity::class,
    PokemonTypesEntity::class,
    PokemonStatsEntity::class,
    PokemonSpritesEntity::class
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