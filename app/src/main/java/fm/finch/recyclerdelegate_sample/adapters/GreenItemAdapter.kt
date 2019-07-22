package fm.finch.recyclerdelegate_sample.adapters

import android.view.View
import android.view.ViewGroup
import fm.finch.recycler_delegate.AdapterDelegate
import fm.finch.recycler_delegate.BaseViewHolder
import fm.finch.recycler_delegate.ViewData
import fm.finch.recyclerdelegate_sample.R
import fm.finch.recyclerdelegate_sample.models.GreenItemViewData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_red.vText

class GreenItemAdapter(
    private val onClick: () -> Unit
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isValidType(viewData: ViewData) = viewData is GreenItemViewData

    inner class ViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder(parent, R.layout.view_green), LayoutContainer {

        init {
            itemView.setOnClickListener {
                onClick()
            }
        }

        override val containerView: View?
            get() = itemView.rootView

        override fun bind(viewData: ViewData) {
            viewData as GreenItemViewData

            vText.text = viewData.text
        }
    }
}