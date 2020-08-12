package saxal.me.saxapokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.databinding.ListPokedexTileBinding

class PokedexListAdapter(private val data: List<String>) :
    RecyclerView.Adapter<PokedexListAdapter.ViewHolder>() {

    companion object {
        const val TAG = "POKEDEX_ADAPTER"
    }


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
        fun bind(data: String) {
            binding.title = data
        }
    }
}