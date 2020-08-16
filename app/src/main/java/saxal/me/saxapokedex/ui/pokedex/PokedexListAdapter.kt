package saxal.me.saxapokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.constants.TypesId
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
            val primaryType = data.types[0]?.type?.name!!

            binding.title = data.name.capitalize()
            binding.id = "#${data.id}"
            binding.imageUrl = data.sprites.other.official_artwork.front_default
            binding.type1 = primaryType.capitalize()

            if(data.types.size > 1) {
                binding.type2 = data.types[1]!!.type.name.capitalize()
            }

            setupCellForType(binding, primaryType)
        }
    }

    fun updateData(updated: List<Pokemon>) {
        data = updated
        notifyDataSetChanged()
    }

    fun setupCellForType(binding: ListPokedexTileBinding, type: String) {
        when (type) {
            TypesId.FIRE -> {
                binding.card.setBackgroundResource(R.drawable.card_bg_fire)
                binding.pokeball.setImageResource(R.drawable.card_pokeball_fire)
                binding.pokedexTag.setBackgroundResource(R.drawable.card_tag_fire)
                binding.pokedexTag2.setBackgroundResource(R.drawable.card_tag_fire)
            }
            TypesId.WATER -> {
                binding.card.setBackgroundResource(R.drawable.card_bg_water)
                binding.pokeball.setImageResource(R.drawable.card_pokeball_water)
                binding.pokedexTag.setBackgroundResource(R.drawable.card_tag_water)
                binding.pokedexTag2.setBackgroundResource(R.drawable.card_tag_water)
            }
            TypesId.GRASS -> {
                binding.card.setBackgroundResource(R.drawable.card_bg_grass)
                binding.pokeball.setImageResource(R.drawable.card_pokeball_grass)
                binding.pokedexTag.setBackgroundResource(R.drawable.card_tag_grass)
                binding.pokedexTag2.setBackgroundResource(R.drawable.card_tag_grass)
            }
            TypesId.ELECTRIC -> {
                binding.card.setBackgroundResource(R.drawable.card_bg_electric)
                binding.pokeball.setImageResource(R.drawable.card_pokeball_electric)
                binding.pokedexTag.setBackgroundResource(R.drawable.card_tag_electric)
                binding.pokedexTag2.setBackgroundResource(R.drawable.card_tag_electric)
            }
            else -> {
                binding.card.setBackgroundResource(R.drawable.card_bg_unknown)
                binding.pokeball.setImageResource(R.drawable.card_pokeball_unknown)
                binding.pokedexTag.setBackgroundResource(R.drawable.card_tag_unknown)
                binding.pokedexTag2.setBackgroundResource(R.drawable.card_tag_unknown)
            }
        }
    }
}