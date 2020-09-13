package saxal.me.saxapokedex.ui.pokemondetail.pokemonmoves

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import saxal.me.saxapokedex.api.model.Moves
import saxal.me.saxapokedex.databinding.ListPokedexTileBinding
import saxal.me.saxapokedex.util.GenericListAdapter


class PokemonMovesListAdapter(val data: List<Moves>): GenericListAdapter<Moves>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericListAdapter.ViewHolder<Moves> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPokedexTileBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    class ViewHolder(val binding: ListPokedexTileBinding): GenericListAdapter.ViewHolder<Moves>(binding) {
        override fun bind(data: Moves) {
            Log.i("BIND", "BIND DATA")
        }
    }
}