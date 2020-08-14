package saxal.me.saxapokedex.api.database.dao

import androidx.room.*
import saxal.me.saxapokedex.api.database.entity.PokemonEntity
import saxal.me.saxapokedex.api.model.Pokemon


@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: List<PokemonEntity>)
}