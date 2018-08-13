package fm.finch.recyclerdelegate.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate.R
import fm.finch.recyclerdelegate.delegate.AdapterDelegate
import fm.finch.recyclerdelegate.delegate.BaseViewHolder
import fm.finch.recyclerdelegate.delegate.PostModel
import kotlinx.android.synthetic.main.red_post.view.*

class RedViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.red_post){

    override fun bind(model: PostModel) = itemView.run{
        model as RedPostModel

        vText.text = model.text

        return@run
    }
}

data class RedPostModel(
        val text: String
) : PostModel

class RedPostDelegate : AdapterDelegate{

    override fun onCreateViewHolder(parent: ViewGroup)
            = RedViewHolder(parent)

    override fun isValidType(postModel: PostModel)
            = postModel is RedPostModel
}