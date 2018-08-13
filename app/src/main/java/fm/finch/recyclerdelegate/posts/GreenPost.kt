package fm.finch.recyclerdelegate.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate.R
import fm.finch.recyclerdelegate.delegate.AdapterDelegate
import fm.finch.recyclerdelegate.delegate.BaseViewHolder
import fm.finch.recyclerdelegate.delegate.PostModel
import kotlinx.android.synthetic.main.red_post.view.*

class GreenViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.green_post){

    override fun bind(model: PostModel) = itemView.run{
        model as GreenPostModel

        vText.text = model.text

        return@run
    }
}

data class GreenPostModel(
        val text: String
) : PostModel

class GreenPostDelegate : AdapterDelegate{

    override fun onCreateViewHolder(parent: ViewGroup)
            = GreenViewHolder(parent)

    override fun isValidType(postModel: PostModel)
            = postModel is GreenPostModel
}