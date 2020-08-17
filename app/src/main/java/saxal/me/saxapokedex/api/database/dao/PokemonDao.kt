package saxal.me.saxapokedex.api.database.dao

import androidx.room.*
import saxal.me.saxapokedex.api.database.entity.*

@Dao
interface PokemonDao {
    @Transaction
    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonWithTypesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSprites(sprites: List<PokemonSpritesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTypes(types: List<PokemonTypesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStats(stats: List<PokemonStatsEntity>)
}