package saxal.me.saxapokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.constants.TypesId
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : DialogFragment() {

    companion object {
        const val POKEMON_ID = "pokemon"
        const val POKEMON_TYPE = "pokemon_type"
        const val TAG = "POKEMON_DETAILS"

        fun newInstance(pokemon: Pokemon): PokemonDetailFragment {
            return PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(POKEMON_ID, pokemon.id)
                    putString(POKEMON_TYPE, pokemon.primaryType)
                }
            }
        }
    }

    private val viewModel: PokemonDetailViewModel by viewModels()

    lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))

        arguments?.let { args ->
            viewModel.pokemonId.value = args.getInt(POKEMON_ID)
            setupPokemonDetailsUI(binding, args.getString(POKEMON_TYPE)!!)
        }

        viewModel.loadPokemonById.observe(viewLifecycleOwner, Observer { pokeResult ->
            if (pokeResult.data != null) {
                setupText(binding, pokeResult.data)
            }
        })

        // setup back button
        binding.pokemonNavbar.backButton.setOnClickListener {
            dismiss()
        }

        // set up pager
        binding.pokemonDetailPager.adapter = DemoCollectionAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(binding.tabLayout, binding.pokemonDetailPager) { tab, position ->
            tab.text = when((position + 1).toString()) {
                "1" -> "About"
                "2" -> "Stats"
                "3" -> "Form"
                "4" -> "Moves"
                else -> "Tab ${position+1}"
            }
        }.attach()
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
        val color = resources.getColor(backgroundColorId)

        binding.pokemonProfileView.setBackgroundColor(color)
        binding.pokemonNavbar.setBackgroundColor(color)
        binding.pokeballView.setImageResource(pokeballResourceId)
        binding.pokemonTypeView.setBackgroundResource(tagResourceId)
        binding.pokemonType2View.setBackgroundResource(tagResourceId)
    }

    private fun setupText(binding: FragmentPokemonDetailBinding, pokemon: Pokemon) {
        binding.pokemonImageUrl = pokemon.sprites.other.official_artwork.front_default
        binding.pokemonName = pokemon.displayName
        binding.pokemonType = pokemon.displayPrimaryType
        binding.pokemonType2 = pokemon.displaySecondaryType
        binding.pokemonId = pokemon.displayId
    }


    // TODO: refactor into shared PokemonTypeResources data class
    private fun setupPokemonDetailsUI(binding: FragmentPokemonDetailBinding, type: String) {
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