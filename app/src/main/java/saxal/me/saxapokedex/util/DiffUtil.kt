package saxal.me.saxapokedex.util

import androidx.recyclerview.widget.DiffUtil

interface GenericId {
    val id: Int
}

class AdapterDiff<T: GenericId>(private val oldList: List<T>, private val newList: List<T>): DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val id = oldList[oldItemPosition].id
        val id1 = newList[newItemPosition].id

        return id == id1
    }
}