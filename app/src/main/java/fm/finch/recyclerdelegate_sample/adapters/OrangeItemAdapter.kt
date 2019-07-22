package fm.finch.recyclerdelegate_sample.adapters

import android.view.View
import android.view.ViewGroup
import fm.finch.recycler_delegate.AdapterDelegate
import fm.finch.recycler_delegate.BaseViewHolder
import fm.finch.recycler_delegate.ViewData
import fm.finch.recyclerdelegate_sample.R
import fm.finch.recyclerdelegate_sample.models.OrangeItemViewData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_red.vText

class OrangeItemAdapter : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isValidType(viewData: ViewData) = viewData is OrangeItemViewData

    inner class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder(parent, R.layout.view_orange), LayoutContainer {

        override val containerView: View?
            get() = itemView.rootView

        override fun bind(viewData: ViewData) {
            viewData as OrangeItemViewData

            vText.text = viewData.text
        }
    }
}