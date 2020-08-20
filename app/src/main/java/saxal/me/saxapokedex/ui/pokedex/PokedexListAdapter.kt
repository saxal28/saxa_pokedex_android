package saxal.me.saxapokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
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
            val primaryType = data.types[0].type.name

            binding.title = data.name.capitalize()
            binding.id = "#${data.id}"
            binding.imageUrl = data.sprites.other.official_artwork.front_default
            binding.type1 = primaryType.capitalize()

            if (data.types.size > 1) {
                binding.type2 = data.types[1].type.name.capitalize()
            }

            binding.card.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                navController.navigate(R.id.action_pokedexFragment2_to_pokemonDetailFragment)
                PokedexFragmentDirections.actionPokedexFragment2ToPokemonDetailFragment(pokemonId = data.id)
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


            TypesId.ROCK ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_rock,
                    pokeballResourceId = R.drawable.card_pokeball_rock,
                    tagResourceId = R.drawable.card_tag_rock
                )

            TypesId.GROUND ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_ground,
                    pokeballResourceId = R.drawable.card_pokeball_ground,
                    tagResourceId = R.drawable.card_tag_ground
                )

            TypesId.POISON ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_poison,
                    pokeballResourceId = R.drawable.card_pokeball_poison,
                    tagResourceId = R.drawable.card_tag_poison
                )

            TypesId.GHOST ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_ghost,
                    pokeballResourceId = R.drawable.card_pokeball_ghost,
                    tagResourceId = R.drawable.card_tag_ghost
                )

            TypesId.PSYCHIC ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_psychic,
                    pokeballResourceId = R.drawable.card_pokeball_psychic,
                    tagResourceId = R.drawable.card_tag_psychic
                )

            TypesId.ICE ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_ice,
                    pokeballResourceId = R.drawable.card_pokeball_ice,
                    tagResourceId = R.drawable.card_tag_ice
                )

            TypesId.STEEL ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_steel,
                    pokeballResourceId = R.drawable.card_pokeball_steel,
                    tagResourceId = R.drawable.card_tag_steel
                )

            TypesId.FIGHTING ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_fighting,
                    pokeballResourceId = R.drawable.card_pokeball_fighting,
                    tagResourceId = R.drawable.card_tag_fighting
                )

            TypesId.FLYING ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_flying,
                    pokeballResourceId = R.drawable.card_pokeball_flying,
                    tagResourceId = R.drawable.card_tag_flying
                )

            TypesId.DRAGON ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_dragon,
                    pokeballResourceId = R.drawable.card_pokeball_dragon,
                    tagResourceId = R.drawable.card_tag_dragon
                )

            TypesId.FAIRY ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_fairy,
                    pokeballResourceId = R.drawable.card_pokeball_fairy,
                    tagResourceId = R.drawable.card_tag_fairy
                )

            TypesId.NORMAL ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_normal,
                    pokeballResourceId = R.drawable.card_pokeball_normal,
                    tagResourceId = R.drawable.card_tag_normal
                )
            TypesId.BUG ->
                setupUI(
                    binding,
                    backgroundResourceId = R.drawable.card_bg_bug,
                    pokeballResourceId = R.drawable.card_pokeball_bug,
                    tagResourceId = R.drawable.card_tag_bug
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