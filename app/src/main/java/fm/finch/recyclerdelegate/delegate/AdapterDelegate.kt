package fm.finch.recyclerdelegate.delegate

import android.view.ViewGroup

interface AdapterDelegate{

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun isValidType(postModel: PostModel): Boolean
}