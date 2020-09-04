package saxal.me.saxapokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.repository.PokedexRepository

class PokedexViewModel : ViewModel() {
    private val pokedexRepo = PokedexRepository()

    val pokemonResult = pokedexRepo.listPokemon

    val allPokemon: MutableLiveData<List<Pokemon>> =  MutableLiveData(emptyList())
    val searchPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData(emptyList())

    val searchText= MutableLiveData("")

    fun searchPokemon() {
        val searchText = searchText.value ?: ""
        Log.i("Search", searchText)

        searchPokemon.value = allPokemon.value

        if(searchText.isEmpty()) {
            searchPokemon.value = allPokemon.value
        } else {
            searchPokemon.value = allPokemon.value!!.filter { it.name.toLowerCase().contains(searchText) }
        }
    }
}