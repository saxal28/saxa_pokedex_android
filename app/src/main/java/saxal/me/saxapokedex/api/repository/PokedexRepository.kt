package saxal.me.saxapokedex.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.*
import retrofit2.*
import saxal.me.saxapokedex.api.model.PokeListResult
import saxal.me.saxapokedex.api.pokeService
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.constants.LoadingStatus
import java.lang.Exception
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

class Timestamp {
    companion object {
        fun time() = Date().time
        fun getTimestampDifference(
            startTime: Long,
            endTime: Long,
            tag: String? = "DURATION"
        ): String {
            val diff: Long = endTime - startTime
            val seconds = diff / 1000.0000

            Log.i("$tag", "seconds: $seconds")
            Log.i("$tag", "milliseconds: $diff")

            return "Duration: "
        }
    }
}

class PokedexRepository {

    // coroutine + live data
    val allPokemon: LiveData<PokeListResult<Pokemon>> = liveData {

        emit(PokeListResult(loading = LoadingStatus.LOADING))
        val startTime = Timestamp.time()

        try {
            var list: List<Pokemon> = mutableListOf()

            val pokemon = pokeService.listPokemon().await()
            val lstOfReturnData = ConcurrentLinkedQueue<Response<Pokemon>>()

            coroutineScope {
                pokemon.results.map { it.name }.forEach { name ->
                    launch(Dispatchers.IO) {
                        lstOfReturnData.add(pokeService.getPokemonInfo(name).execute())
                    }
                }
            }

            list = lstOfReturnData.map { it.body()!! }.sortedBy { it.id }

            val endTime = Timestamp.time()
            Timestamp.getTimestampDifference(startTime, endTime)

            emit(PokeListResult(loading = LoadingStatus.FINISHED, data = list))

        } catch (exception: Exception) {
            Log.e("Exception", "$exception")
            emit(
                PokeListResult(
                    loading = LoadingStatus.FINISHED,
                    errorMessage = exception.message ?: "Error!"
                )
            )
        }
    }


    // TODO: create cool wrapper
//    fun getPokedexPokemon(): LiveData<List<PokedexPokemon>> {
//        val data: MutableLiveData<List<PokedexPokemon>> = MutableLiveData(emptyList())
//
//        pokeService.listPokemon().enqueue(object : Callback<PokedexPokemonResults> {
//            override fun onResponse(
//                call: Call<PokedexPokemonResults>,
//                response: Response<PokedexPokemonResults>
//            ) {
//                val body = response.body()?.results ?: emptyList()
//
//                data.value = body.map {
//                    it.apply {
//                        this.url = getPokedexImage(this.url)
//
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<PokedexPokemonResults>, t: Throwable) {
//                Log.i("ERROR", t.localizedMessage ?: "error")
//            }
//
//        })
//
//        return data
//    }
}