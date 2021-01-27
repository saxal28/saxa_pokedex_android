package saxal.me.saxapokedex.ui.pokemondetail

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.data.PokemonTypeResource
import saxal.me.saxapokedex.data.PokemonTypeResources
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailBinding
import saxal.me.saxapokedex.ui.GlobalViewModel

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

    // shared viewmodel
    private val sharedViewModel: PokemonDetailViewModel by activityViewModels()
    private val globalViewModel: GlobalViewModel by activityViewModels()

    lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var adapter: DemoCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))

        arguments?.let { args ->
            sharedViewModel.pokemonId.value = args.getInt(POKEMON_ID)
            setupPokemonDetailsUI(binding, args.getString(POKEMON_TYPE)!!)
        }

        sharedViewModel.loadPokemonDetails.observe(viewLifecycleOwner, Observer { pokeResult ->
            if (pokeResult.data != null) {
                val pokemon = pokeResult.data

                sharedViewModel.pokemon.value = pokemon

                setupText(binding, pokemon)
            }
        })

        // setup back button
        binding.pokemonNavbar.backButton.setOnClickListener { dismiss() }

        // set up pager
        adapter = DemoCollectionAdapter(this, sharedViewModel.pokemonId.value!!)
        binding.pokemonDetailPager.adapter = adapter
        binding.pokemonDetailPager.setCurrentItem(0, false)

        binding.pokemonDetailPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                resizeViewPager(position)
            }
        })

        binding.currentVersion.text = globalViewModel.currentVersion.capitalize()

        dialog?.window?.setWindowAnimations(R.style.DialogAnimation)

        return binding.root
    }

    // in order to get dynamically sized fragments in tabbar,
    // have to resize view pager dynamically
    fun resizeViewPager(position: Int) {
        Handler().postDelayed({
            val view = adapter.fragments[position].view // ... get the view
            val pager = binding.pokemonDetailPager
            view?.post {
                val wMeasureSpec = MeasureSpec.makeMeasureSpec(view.width, MeasureSpec.EXACTLY)
                val hMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                view.measure(wMeasureSpec, hMeasureSpec)

                pager.layoutParams = (pager.layoutParams as LinearLayout.LayoutParams).apply {
                    this.height = view.measuredHeight + 100
                }
            }
        }, 10)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(binding.tabLayout, binding.pokemonDetailPager) { tab, position ->
            tab.text = when ((position + 1).toString()) {
                "1" -> "About"
                "2" -> "Stats"
                "3" -> "Form"
                "4" -> "Moves"
                else -> "Tab ${position + 1}"
            }
        }.attach()
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    private fun setupUIForType(
        binding: FragmentPokemonDetailBinding,
        resource: PokemonTypeResource
    ) {
        val color = resources.getColor(resource.backgroundColorResourceId)

        binding.pokemonProfileView.setBackgroundColor(color)
        binding.pokemonNavbar.setBackgroundColor(color)
        binding.pokeballView.setImageResource(resource.pokeballResourceId)
        binding.pokemonTypeView.setBackgroundResource(resource.tagResourceId)
        binding.pokemonType2View.setBackgroundResource(resource.tagResourceId)
    }

    private fun setupText(binding: FragmentPokemonDetailBinding, pokemon: PokemonDetail) {
        binding.pokemonImageUrl = pokemon.sprites.other.official_artwork.front_default
        binding.pokemonName = pokemon.displayName
        binding.pokemonType = pokemon.displayPrimaryType
        binding.pokemonType2 = pokemon.displaySecondaryType
        binding.pokemonId = pokemon.displayId
    }

    private fun setupPokemonDetailsUI(binding: FragmentPokemonDetailBinding, type: String) {
        val resource = PokemonTypeResources.forType(type)
        setupUIForType(binding, resource)
    }

}