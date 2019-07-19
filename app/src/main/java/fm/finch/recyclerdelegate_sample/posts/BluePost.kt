package fm.finch.recyclerdelegate_sample.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate_sample.R
import fm.finch.recycler_delegate.AdapterDelegate
import fm.finch.recycler_delegate.BaseViewHolder
import fm.finch.recycler_delegate.ViewData
import kotlinx.android.synthetic.main.red_post.view.*

class BlueViewHolder(parent: ViewGroup) : fm.finch.recycler_delegate.BaseViewHolder(parent, R.layout.blue_post){

    override fun bind(model: ViewData) = itemView.run{
        model as BluePostModel

        vText.text = model.text

        return@run
    }
}

data class BluePostModel(
        val text: String
) : ViewData

class BluePostDelegate : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup)
            = BlueViewHolder(parent)

    override fun isValidType(postModel: ViewData)
            = postModel is BluePostModel
}