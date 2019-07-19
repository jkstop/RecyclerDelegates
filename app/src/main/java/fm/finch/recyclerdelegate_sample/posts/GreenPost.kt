package fm.finch.recyclerdelegate_sample.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate_sample.R
import fm.finch.recycler_delegate.AdapterDelegate
import fm.finch.recycler_delegate.BaseViewHolder
import fm.finch.recycler_delegate.ViewData
import kotlinx.android.synthetic.main.red_post.view.*

class GreenViewHolder(parent: ViewGroup) : fm.finch.recycler_delegate.BaseViewHolder(parent, R.layout.green_post){

    override fun bind(model: fm.finch.recycler_delegate.ViewData) = itemView.run{
        model as GreenPostModel

        vText.text = model.text

        return@run
    }
}

data class GreenPostModel(
        val text: String
) : fm.finch.recycler_delegate.ViewData

class GreenPostDelegate : fm.finch.recycler_delegate.AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup)
            = GreenViewHolder(parent)

    override fun isValidType(postModel: fm.finch.recycler_delegate.ViewData)
            = postModel is GreenPostModel
}