package saxal.me.saxapokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokedexViewModel : ViewModel() {

    private val _data = MutableLiveData(
        listOf(
            "Charmander",
            "Charmeleon",
            "Charizard",
            "Blastoise",
            "Charmander",
            "Charmeleon",
            "Charizard",
            "Blastoise",
            "Charmander",
            "Charmeleon",
            "Charizard",
            "Blastoise",
            "Charmander",
            "Charmeleon",
            "Charizard",
            "Blastoise"
        )
    )

    val data: LiveData<List<String>>
        get() = _data
}