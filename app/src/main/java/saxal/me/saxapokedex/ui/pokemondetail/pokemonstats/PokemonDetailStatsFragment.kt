package saxal.me.saxapokedex.ui.pokemondetail.pokemonstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import saxal.me.saxapokedex.api.model.PokemonDetail
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailStatsBinding
import saxal.me.saxapokedex.ui.pokemondetail.PokemonDetailViewModel

class PokemonDetailStatsFragment: Fragment() {

    private val sharedViewModel: PokemonDetailViewModel by activityViewModels()
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
        sharedViewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon ->
            setupStats(pokemon!!)
        })
    }

    private fun setupStats(pokemon: PokemonDetail) {
        binding.statHp.statValue.text = pokemon.hpStat?.base_stat?.toString()
        binding.statAttack.statValue.text = pokemon.attackStat?.base_stat?.toString()
        binding.statDefense.statValue.text = pokemon.defenseStat?.base_stat?.toString()
        binding.statSpecialAttack.statValue.text = pokemon.specialAttackStat?.base_stat?.toString()
        binding.statSpecialDefense.statValue.text = pokemon.specialDefenseStat?.base_stat?.toString()
        binding.statSpeed.statValue.text = pokemon.speedStat?.base_stat?.toString()
        binding.statTotal.statValue.text = pokemon.allStats.toInt().toString()

        binding.hpProgress = pokemon.hpStat?.statPercentage ?: 0.00
        binding.attackProgress = pokemon.attackStat?.statPercentage ?: 0.00
        binding.defenseProgress = pokemon.defenseStat?.statPercentage ?: 0.00
        binding.specialAttackProgress = pokemon.specialAttackStat?.statPercentage ?: 0.00
        binding.specialDefenseProgress = pokemon.specialDefenseStat?.statPercentage ?: 0.00
        binding.speedProgress = pokemon.speedStat?.statPercentage ?: 0.00
        binding.allStatsProgress = pokemon.allStatPercentage
    }
}