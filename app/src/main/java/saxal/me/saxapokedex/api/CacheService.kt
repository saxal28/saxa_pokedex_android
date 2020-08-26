package saxal.me.saxapokedex.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import saxal.me.saxapokedex.api.database.Database
import saxal.me.saxapokedex.api.database.entity.PokemonSpeciesEntity
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.model.PokemonSpecies
import java.lang.Exception

class CacheService {
    // load from DB and Convert to UI
    companion object {
        suspend fun loadPokemon(): List<Pokemon> {
            var pokemon: List<Pokemon> = listOf()

            withContext(Dispatchers.IO) {
                try {
                    val dao = Database.instance?.pokemonDao()
                    val pokemonEntities = dao?.getAll() ?: listOf()
                    pokemon = pokemonEntities.map { it.mapToUIModel() }
                } catch (e: Exception) {
                    Log.i("EX", e.message ?: "db error")
                }
            }

            return pokemon
        }

        suspend fun getPokemonById(pokemonId: Int): Pokemon? {
            var pokemon: Pokemon? = null

            withContext(Dispatchers.IO) {
                try {
                    val dao = Database.instance?.pokemonDao()
                    val loadedPokemon = dao?.getPokemon(pokemonId)
                    pokemon = loadedPokemon?.mapToUIModel()
                } catch (e: Exception) {
                    Log.i("EX", e.message ?: "db error")
                }
            }

            return pokemon
        }

        // save pokemon
        suspend fun savePokemon(pokemon: List<Pokemon>) {
            withContext(Dispatchers.IO) {
                try {
                    val dao = Database.instance?.pokemonDao()!!
                    // insert pokmeon
                    dao.insertPokemon(pokemon.map { it.mapToEntity() })
                    // insert sprite
                    dao.insertSprites(pokemon.map { it.sprites.mapToEntity(it.id) })
                    // insert types
                    val types = pokemon.flatMap { pokemon -> pokemon.types.map { it.mapToEntity(pokemon.id) } }
                    dao.insertTypes(types)
                    // insert stats
                    val stats = pokemon.flatMap { pokemon -> pokemon.stats.map { it.mapToEntity(pokemon.id) } }
                    dao.insertStats(stats)
                } catch (e: Exception) {
                    Log.i("EX", e.message ?: "db error")
                }
            }
        }

        suspend fun savePokemonSpecies(data: PokemonSpeciesEntity) {
            withContext(Dispatchers.IO) {
                try {
                    val dao = Database.instance?.pokemonDao()!!
                    // insert pokeSpecies
                    dao.insertPokemonSpecies(data.pokemonSpecies)
                    // save egg groups
                    dao.insertEggGroups(data.egg_groups)
                    // save flavor entries
                    dao.insertFlavorTextEntries(data.flavor_text_entries)
                } catch (e: Exception) {

                }
            }
        }
        suspend fun getPokemonSpecies(pokemonId: Int): PokemonSpecies? {
            var species: PokemonSpecies? = null

            withContext(Dispatchers.IO) {
                try {
                    val dao = Database.instance?.pokemonDao()!!
                    val data = dao.getPokemonSpecies(pokemonId)
                    species = data?.mapToUIModel()
                } catch (e: Exception) {

                }
            }
            return species
        }
    }
}