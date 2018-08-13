package fm.finch.recyclerdelegate.delegate

import android.support.v7.util.DiffUtil

class DiffCallback(
        val old: List<PostModel>,
        val new: List<PostModel>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old[oldItemPosition] == new[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old[oldItemPosition] == new[newItemPosition]
}