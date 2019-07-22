package fm.finch.recycler_delegate

import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class RecyclerDelegatesAdapter(
    vararg delegates: AdapterDelegate
) : RecyclerView.Adapter<BaseViewHolder>() {

    protected val dataModels = mutableListOf<ViewData>()
    private val delegateManager = AdapterDelegateManager()

    private val diffCallback = object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            changeListener(position)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {
            insertListener.invoke(position)
        }
    }

    var insertListener: (startPosition: Int) -> Unit = {}
    var changeListener: (position: Int) -> Unit = {}

    init {
        delegates.forEach {
            delegateManager.addDelegate(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegateManager.getDelegate(
            viewType
        ).onCreateViewHolder(parent)

    override fun getItemCount(): Int = dataModels.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(dataModels[position])

    override fun getItemViewType(position: Int): Int =
        delegateManager.getItemViewType(dataModels[position])

    fun add(input: ViewData) = with(dataModels) {
        add(input)
        notifyItemInserted(size)
    }

    fun add(input: List<ViewData>) = with(dataModels) {
        val currentSize = size
        addAll(input)
        notifyItemRangeInserted(currentSize, size)
    }

    fun replaceNoAnim(input: List<ViewData>) = with(dataModels) {
        clear()
        addAll(input)
        notifyDataSetChanged()
    }

    fun replace(input: List<ViewData>) = with(dataModels) {
        val old = dataModels.toList()
        clear()
        addAll(input)

        DiffUtil
            .calculateDiff(DiffCallback(old, dataModels), true)
            .apply {
                dispatchUpdatesTo(diffCallback)
            }
            .dispatchUpdatesTo(this@RecyclerDelegatesAdapter)
    }

    fun remove(input: ViewData) = with(dataModels) {
        val index = indexOf(input)
        removeAt(index)
        notifyItemRemoved(index)
    }

    fun change(input: ViewData) = with(dataModels) {
        if (contains(input)) {
            val index = indexOf(input)
            removeAt(index)
            add(index, input)
            notifyItemChanged(index)
            changeListener(index)
        }
    }

    fun change(oldItem: ViewData, newItem: ViewData) = with(dataModels) {
        if (contains(oldItem)) {
            val index = indexOf(oldItem)
            removeAt(index)
            add(index, newItem)
            notifyItemChanged(index)
            changeListener(index)
        }
    }

    fun clear() = with(dataModels) {
        val size = size
        clear()
        notifyItemRangeRemoved(0, size)
    }

    fun clearAfter(index: Int) = with(dataModels) {
        val size = size
        subList(index, size).clear()
        notifyItemRangeRemoved(index, size)
    }

    fun getItem(position: Int): ViewData {
        return dataModels[position]
    }
}