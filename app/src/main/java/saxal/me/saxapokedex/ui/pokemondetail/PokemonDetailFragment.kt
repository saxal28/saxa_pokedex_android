package saxal.me.saxapokedex.ui.pokemondetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.constants.TypesId
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : DialogFragment() {

    companion object {
        const val POKEMON_ID = "pokemon"
        const val TAG = "POKEMON_DETAILS"

        fun newInstance(pokemon: Pokemon): PokemonDetailFragment {
            return PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(POKEMON_ID, pokemon.id)
                }
            }
        }
    }

    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))

        arguments?.let { args ->
            viewModel.pokemonId.value = args.getInt(POKEMON_ID)
        }

        viewModel.loadPokemonById.observe(viewLifecycleOwner, Observer { pokeResult ->
            if (pokeResult.data != null) {
                Log.i("RESULT", pokeResult.data.toString())
                setupUI(binding, pokeResult.data)
            }
        })

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    private fun setupUIForType(
        binding: FragmentPokemonDetailBinding,
        backgroundColorId: Int,
        pokeballResourceId: Int,
        tagResourceId: Int
    ) {
        binding.pokemonProfileView.setBackgroundColor(resources.getColor(backgroundColorId))
        binding.pokeballView.setImageResource(pokeballResourceId)
        binding.pokemonTypeView.setBackgroundResource(tagResourceId)
        binding.pokemonType2View.setBackgroundResource(tagResourceId)
    }

    private fun setupUI(binding: FragmentPokemonDetailBinding, pokemon: Pokemon) {

        binding.pokemonImageUrl = pokemon.sprites.other.official_artwork.front_default
        binding.pokemonName = pokemon.displayName
        binding.pokemonType = pokemon.displayPrimaryType
        binding.pokemonType2 = pokemon.displaySecondaryType
        binding.pokemonId = pokemon.displayId

        Log.i("POKEMON | Secondary", "${pokemon.name} | ${pokemon.secondaryType}")

        setupCellForType(binding, pokemon.primaryType)
    }


    // TODO: some way to reuse this??
    private fun setupCellForType(binding: FragmentPokemonDetailBinding, type: String) {
        when (type) {
            TypesId.FIRE ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.fire,
                    pokeballResourceId = R.drawable.card_pokeball_fire,
                    tagResourceId = R.drawable.card_tag_fire
                )

            TypesId.WATER ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.water,
                    pokeballResourceId = R.drawable.card_pokeball_water,
                    tagResourceId = R.drawable.card_tag_water
                )
            TypesId.GRASS ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.grass,
                    pokeballResourceId = R.drawable.card_pokeball_grass,
                    tagResourceId = R.drawable.card_tag_grass
                )
            TypesId.ELECTRIC ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.electric,
                    pokeballResourceId = R.drawable.card_pokeball_electric,
                    tagResourceId = R.drawable.card_tag_electric
                )


            TypesId.ROCK ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.rock,
                    pokeballResourceId = R.drawable.card_pokeball_rock,
                    tagResourceId = R.drawable.card_tag_rock
                )

            TypesId.GROUND ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.ground,
                    pokeballResourceId = R.drawable.card_pokeball_ground,
                    tagResourceId = R.drawable.card_tag_ground
                )

            TypesId.POISON ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.poison,
                    pokeballResourceId = R.drawable.card_pokeball_poison,
                    tagResourceId = R.drawable.card_tag_poison
                )

            TypesId.GHOST ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.ghost,
                    pokeballResourceId = R.drawable.card_pokeball_ghost,
                    tagResourceId = R.drawable.card_tag_ghost
                )

            TypesId.PSYCHIC ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.psychic,
                    pokeballResourceId = R.drawable.card_pokeball_psychic,
                    tagResourceId = R.drawable.card_tag_psychic
                )

            TypesId.ICE ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.ice,
                    pokeballResourceId = R.drawable.card_pokeball_ice,
                    tagResourceId = R.drawable.card_tag_ice
                )

            TypesId.STEEL ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.steel,
                    pokeballResourceId = R.drawable.card_pokeball_steel,
                    tagResourceId = R.drawable.card_tag_steel
                )

            TypesId.FIGHTING ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.fighting,
                    pokeballResourceId = R.drawable.card_pokeball_fighting,
                    tagResourceId = R.drawable.card_tag_fighting
                )

            TypesId.FLYING ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.flying,
                    pokeballResourceId = R.drawable.card_pokeball_flying,
                    tagResourceId = R.drawable.card_tag_flying
                )

            TypesId.DRAGON ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.dragon,
                    pokeballResourceId = R.drawable.card_pokeball_dragon,
                    tagResourceId = R.drawable.card_tag_dragon
                )

            TypesId.FAIRY ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.fairy,
                    pokeballResourceId = R.drawable.card_pokeball_fairy,
                    tagResourceId = R.drawable.card_tag_fairy
                )

            TypesId.NORMAL ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.normal,
                    pokeballResourceId = R.drawable.card_pokeball_normal,
                    tagResourceId = R.drawable.card_tag_normal
                )
            TypesId.BUG ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.bug,
                    pokeballResourceId = R.drawable.card_pokeball_bug,
                    tagResourceId = R.drawable.card_tag_bug
                )

            else ->
                setupUIForType(
                    binding,
                    backgroundColorId = R.color.unknown,
                    pokeballResourceId = R.drawable.card_pokeball_unknown,
                    tagResourceId = R.drawable.card_tag_unknown
                )
        }
    }

}