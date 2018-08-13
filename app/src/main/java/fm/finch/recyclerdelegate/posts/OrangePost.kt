package fm.finch.recyclerdelegate.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate.R
import fm.finch.recyclerdelegate.delegate.AdapterDelegate
import fm.finch.recyclerdelegate.delegate.BaseViewHolder
import fm.finch.recyclerdelegate.delegate.PostModel
import kotlinx.android.synthetic.main.red_post.view.*

class OrangeViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.orange_post){

    override fun bind(model: PostModel) = itemView.run{
        model as OrangePostModel

        vText.text = model.text

        return@run
    }
}

data class OrangePostModel(
        val text: String
) : PostModel

class OrangePostDelegate : AdapterDelegate{

    override fun onCreateViewHolder(parent: ViewGroup)
            = OrangeViewHolder(parent)

    override fun isValidType(postModel: PostModel)
            = postModel is OrangePostModel
}