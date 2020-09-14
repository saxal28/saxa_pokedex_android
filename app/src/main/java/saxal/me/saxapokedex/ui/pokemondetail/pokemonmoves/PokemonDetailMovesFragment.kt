package saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.api.model.Moves
import saxal.me.saxapokedex.databinding.FragmentPokemonDetailMovesBinding
import saxal.me.saxapokedex.ui.GlobalViewModel
import saxal.me.saxapokedex.ui.pokemondetail.PokemonDetailViewModel

class PokemonDetailMovesFragment : Fragment() {
    private val sharedViewModel: PokemonDetailViewModel by activityViewModels()
    private val globalViewModel: GlobalViewModel by activityViewModels()

    private lateinit var listAdapter: PokemonMovesListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonDetailMovesBinding.inflate(LayoutInflater.from(context))

        listAdapter =  PokemonMovesListAdapter(getVersionMovesForPokemon())
        viewManager = LinearLayoutManager(context)

        binding.movesRecycler.apply {
            adapter = listAdapter
            layoutManager = viewManager
        }

        return binding.root
    }

    private fun getVersionMovesForPokemon(): List<Moves> {
        val currentVersion = globalViewModel.currentVersion
        val moves = sharedViewModel.pokemon.value!!.moves

        val newMoves = moves
            .map { move ->
                move.apply {
                    this.version_group_details = version_group_details.filter {
                        val versionName = it.version_group.name
                        if (versionName.contains("-")) {
                            var match = false
                            versionName.split("-").forEach { part ->
                                if (part == currentVersion) {
                                    match = true
                                }
                            }
                            match
                        } else {
                            it.version_group.name == currentVersion
                        }
                    }
                }
            }.filter { it.version_group_details.isNotEmpty() }

        return newMoves.sortedBy { it.version_group_details.first().level_learned_at }
    }

    private fun generatePokemonMovesTest(): String {
        val moves = getVersionMovesForPokemon()

        return moves.joinToString(" ") {
            var moveName = it.move.name.split("-").map { it.capitalize() }.joinToString(" ")
            val learnedAtLevel = it.version_group_details.first().level_learned_at

            val howLearned: String = if (learnedAtLevel == 0) {
                "How Learned: ${it.version_group_details.first().move_learn_method.name}"
            } else {
                "Level: $learnedAtLevel"
            }
            "$moveName - $howLearned\n"
        }
    }

//    private fun generatePokemonMovesTest() =
//        sharedViewModel.versionMoves.joinToString(" ") { "${it.move.name} - ${it.version_group_details.map { it.level_learned_at }}\n\n" }

}