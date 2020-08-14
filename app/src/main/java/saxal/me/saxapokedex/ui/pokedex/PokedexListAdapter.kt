package saxal.me.saxapokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.api.model.PokedexPokemon
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.databinding.ListPokedexTileBinding

class PokedexListAdapter(private var data: List<Pokemon>) :
    RecyclerView.Adapter<PokedexListAdapter.ViewHolder>() {

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
        fun bind(data: Pokemon) {
            binding.title = data.name.capitalize()
            binding.imageUrl = data.sprites.other.official_artwork.front_default
        }
    }

    fun updateData(updated: List<Pokemon>) {
        data = updated
        notifyDataSetChanged()
    }
}