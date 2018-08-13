package fm.finch.recyclerdelegate.posts

import android.view.ViewGroup
import fm.finch.recyclerdelegate.R
import fm.finch.recyclerdelegate.delegate.AdapterDelegate
import fm.finch.recyclerdelegate.delegate.BaseViewHolder
import fm.finch.recyclerdelegate.delegate.PostModel
import kotlinx.android.synthetic.main.red_post.view.*

class BlueViewHolder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.blue_post){

    override fun bind(model: PostModel) = itemView.run{
        model as BluePostModel

        vText.text = model.text

        return@run
    }
}

data class BluePostModel(
        val text: String
) : PostModel

class BluePostDelegate : AdapterDelegate{

    override fun onCreateViewHolder(parent: ViewGroup)
            = BlueViewHolder(parent)

    override fun isValidType(postModel: PostModel)
            = postModel is BluePostModel
}