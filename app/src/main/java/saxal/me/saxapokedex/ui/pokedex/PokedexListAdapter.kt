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

            if (data.types.size > 1) {
                binding.type2 = data.types[1]!!.type.name.capitalize()
            }

            setupCellForType(binding, primaryType)
        }
    }

    fun updateData(updated: List<Pokemon>) {
        data = updated
        notifyDataSetChanged()
    }

    private fun setupUI(
        binding: ListPokedexTileBinding,
        backgroundResourceId: Int,
        pokeballResourceId: Int,
        tagResourceId: Int
    ) {
        binding.card.setBackgroundResource(backgroundResourceId)
        binding.pokeball.setImageResource(pokeballResourceId)
        binding.pokedexTag.setBackgroundResource(tagResourceId)
        binding.pokedexTag2.setBackgroundResource(tagResourceId)
    }

    fun setupCellForType(binding: ListPokedexTileBinding, type: String) {
        when (type) {
            TypesId.FIRE ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_fire,
                    pokeballResourceId = R.drawable.card_pokeball_fire,
                    tagResourceId = R.drawable.card_tag_fire
                )

            TypesId.WATER ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_water,
                    pokeballResourceId = R.drawable.card_pokeball_water,
                    tagResourceId = R.drawable.card_tag_water
                )
            TypesId.GRASS ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_grass,
                    pokeballResourceId = R.drawable.card_pokeball_grass,
                    tagResourceId = R.drawable.card_tag_grass
                )
            TypesId.ELECTRIC ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_electric,
                    pokeballResourceId = R.drawable.card_pokeball_electric,
                    tagResourceId = R.drawable.card_tag_electric
                )
            else ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_unknown,
                    pokeballResourceId = R.drawable.card_pokeball_unknown,
                    tagResourceId = R.drawable.card_tag_unknown
                )
        }
    }
}