package fm.finch.recyclerdelegate_sample

import fm.finch.recycler_delegate.RecyclerDelegatesAdapter
import fm.finch.recyclerdelegate_sample.adapters.BlueItemAdapter
import fm.finch.recyclerdelegate_sample.adapters.GreenItemAdapter
import fm.finch.recyclerdelegate_sample.adapters.OrangeItemAdapter
import fm.finch.recyclerdelegate_sample.adapters.RedItemAdapter

class DataAdapter(
    onGreenClick: () -> Unit
) : RecyclerDelegatesAdapter(
    RedItemAdapter(),
    GreenItemAdapter(onGreenClick),
    OrangeItemAdapter(),
    BlueItemAdapter()
)