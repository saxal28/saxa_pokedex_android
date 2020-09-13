package saxal.me.saxapokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.data.PokemonTypeResource
import saxal.me.saxapokedex.data.PokemonTypeResources
import saxal.me.saxapokedex.databinding.ListPokedexTileBinding


// TODO: make generic
class PokkedexListAdapterDiff(private val oldList: List<Pokemon>, private val newList: List<Pokemon>): DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (id, value) = oldList[oldItemPosition]
        val (id1, value1) = newList[newItemPosition]

        return id == id1 && value == value1
    }
}

class PokedexListAdapter(private var data: List<Pokemon>) :
    RecyclerView.Adapter<PokedexListAdapter.ViewHolder>() {

    private val _pokemonToNavigateTo: MutableLiveData<Pokemon?> = MutableLiveData(null)
    val pokemonToNavigateTo: LiveData<Pokemon?>
        get() = _pokemonToNavigateTo

    fun clearActivePokemonId() {
        _pokemonToNavigateTo.value = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPokedexTileBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ListPokedexTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {

            binding.title = pokemon.displayName
            binding.id = pokemon.displayId
            binding.imageUrl = pokemon.sprites.other.official_artwork.front_default
            binding.type1 = pokemon.displayPrimaryType

            binding.type2 = pokemon.displaySecondaryType

            binding.card.setOnClickListener {
                _pokemonToNavigateTo.value = pokemon
            }

            setupCellForType(binding, pokemon.primaryType)
        }
    }

    fun updateData(updated: List<Pokemon>) {
        val diffCallback = PokkedexListAdapterDiff(data, updated)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = updated
        diffResult.dispatchUpdatesTo(this)
    }

    private fun setupUI(
        binding: ListPokedexTileBinding,
       resource: PokemonTypeResource
    ) {
        binding.card.setBackgroundResource(resource.pokedexBackgroundResourceId)
        binding.pokeball.setImageResource(resource.pokeballResourceId)
        binding.pokedexTag.setBackgroundResource(resource.tagResourceId)
        binding.pokedexTag2.setBackgroundResource(resource.tagResourceId)
    }

    fun setupCellForType(binding: ListPokedexTileBinding, type: String) {
        val resource = PokemonTypeResources.forType(type)
        setupUI(binding, resource)
    }
}








