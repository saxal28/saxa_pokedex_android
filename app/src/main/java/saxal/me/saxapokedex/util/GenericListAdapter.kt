package saxal.me.saxapokedex.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import saxal.me.saxapokedex.databinding.ListPokedexTileBinding

// TODO:
// GENERIC LIST ADAPTER

//pass in data, binding, viewholder

abstract class GenericListAdapter<G>(private var data: List<G>) :
    RecyclerView.Adapter<GenericListAdapter.ViewHolder<G>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<G> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPokedexTileBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<G>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    open class ViewHolder<G>(private val binding: ListPokedexTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun bind(data: G){}
    }

    fun updateData(updated: List<G>) {
        val diffCallback = AdapterDiff(data as List<GenericId>, updated as List<GenericId>)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = updated
        diffResult.dispatchUpdatesTo(this)
    }
}