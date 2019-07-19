package fm.finch.recycler_delegate

import android.view.ViewGroup

interface AdapterDelegate{

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun isValidType(postModel: ViewData): Boolean
}