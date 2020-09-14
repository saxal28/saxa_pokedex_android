package saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.MainActivity
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.api.model.Moves
import saxal.me.saxapokedex.databinding.ListPokemonMoveBinding


class PokemonMovesListAdapter(var data: List<Moves>) :
    RecyclerView.Adapter<PokemonMovesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPokemonMoveBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ListPokemonMoveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(move: Moves) {
            val version = move.version_group_details.first()
            val level = version.level_learned_at
            val levelMethodName =  version.move_learn_method.name

            val drawableId = when(levelMethodName) {
                "machine" -> R.drawable.ic_tm
                "tutor" -> R.drawable.ic_tutor
                "egg" -> R.drawable.ic_egg
                else -> R.drawable.ic_levelbackground
            }

            binding.moveImage.setImageDrawable(MainActivity.contextInstance!!.getDrawable(drawableId))

            binding.level = if(level == 0) {
               null
            } else {
                level.toString()
            }

            binding.moveLevel.isVisible = level > 0

            binding.name = move.move.formatName()
        }
    }
}