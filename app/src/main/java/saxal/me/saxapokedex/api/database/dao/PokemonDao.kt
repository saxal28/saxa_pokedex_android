package saxal.me.saxapokedex.api.database.dao

import androidx.room.*
import saxal.me.saxapokedex.api.database.entity.*

@Dao
interface PokemonDao {
    @Transaction
    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonWithTypesEntity>

    @Transaction
    @Query("SELECT * FROM PokemonEntity where pokemonId == :id")
    fun getPokemon(id: Int): PokemonWithTypesEntity

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

    // Pokemon About
    @Transaction
    @Query("SELECT * FROM PokemonSpecieEntity where pokemonId == :id")
    fun getPokemonSpecies(id: Int): PokemonSpeciesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonSpecies(pokemonSpecies: PokemonSpecieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEggGroups(eggGroups: List<EggGroupEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlavorTextEntries(entries: List<FlavorTextEntryEntity>)

}