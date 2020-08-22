package saxal.me.saxapokedex.ui.pokemondetail.pokemonstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import saxal.me.saxapokedex.api.model.Pokemon
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailStatsBinding
import saxal.me.saxapokedex.ui.pokemondetail.PokemonDetailViewModel

class PokemonDetailStatsFragment: Fragment() {

    companion object {
        const val POKEMON_ID = "pokemon_id"
    }

    private val viewModel: PokemonDetailViewModel by viewModels()
    lateinit var binding: FragmentPokemonDetailStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailStatsBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.takeIf { it!!.containsKey(POKEMON_ID) }?.apply {
            viewModel.pokemonId.value = getInt(POKEMON_ID)
        }

        viewModel.loadPokemonById.observe(viewLifecycleOwner, Observer { pokeResult ->
            if (pokeResult.data != null) {
                viewModel.pokemon.value = pokeResult.data
                setupStats(pokeResult.data)
            }
        })
    }

    private fun setupStats(pokemon: Pokemon) {
        binding.statHp.statValue.text = pokemon.hpStat?.base_stat?.toString()
        binding.statAttack.statValue.text = pokemon.attackStat?.base_stat?.toString()
        binding.statDefense.statValue.text = pokemon.defenseStat?.base_stat?.toString()
        binding.statSpecialAttack.statValue.text = pokemon.specialAttackStat?.base_stat?.toString()
        binding.statSpecialDefense.statValue.text = pokemon.specialDefenseStat?.base_stat?.toString()
        binding.statSpeed.statValue.text = pokemon.speedStat?.base_stat?.toString()

        binding.hpProgress = pokemon.hpStat?.statPercentage ?: 0.00
        binding.attackProgress = pokemon.attackStat?.statPercentage ?: 0.00
        binding.defenseProgress = pokemon.defenseStat?.statPercentage ?: 0.00
        binding.specialAttackProgress = pokemon.specialAttackStat?.statPercentage ?: 0.00
        binding.specialDefenseProgress = pokemon.specialDefenseStat?.statPercentage ?: 0.00
        binding.speedProgress = pokemon.speedStat?.statPercentage ?: 0.00
    }
}