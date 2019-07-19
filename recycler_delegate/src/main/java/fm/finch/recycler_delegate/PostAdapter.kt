package fm.finch.recycler_delegate

import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class PostAdapter(
        vararg delegates: AdapterDelegate
) : RecyclerView.Adapter<BaseViewHolder>(){

    protected val postModels = mutableListOf<ViewData>()
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

    val subloadPosition: Int
        get() = postModels.size - 1

    init {
        delegates.forEach {
            delegateManager.addDelegate(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegateManager.getDelegate(
            viewType
        ).onCreateViewHolder(parent)

    override fun getItemCount(): Int = postModels.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(postModels[position])

    override fun getItemViewType(position: Int): Int =
        delegateManager.getItemViewType(postModels[position])

    fun add(input: ViewData) = with(postModels) {
        add(input)
        notifyItemInserted(size)
    }

    fun add(input: List<ViewData>) = with(postModels) {
        val currentSize = size
        addAll(input)
        notifyItemRangeInserted(currentSize, size)
    }

    fun replaceNoAnim(input: List<ViewData>) = with(postModels) {
        clear()
        addAll(input)
        notifyDataSetChanged()
    }

    fun replace(input: List<ViewData>) = with(postModels) {
        val old = postModels.toList()
        clear()
        addAll(input)

        DiffUtil
            .calculateDiff(DiffCallback(old, postModels), true)
            .apply {
                dispatchUpdatesTo(diffCallback)
            }
            .dispatchUpdatesTo(this@PostAdapter)
    }

    fun remove(input: ViewData) = with(postModels) {
        val index = indexOf(input)
        removeAt(index)
        notifyItemRemoved(index)
    }

    fun change(input: ViewData) = with(postModels) {
        if (contains(input)) {
            val index = indexOf(input)
            removeAt(index)
            add(index, input)
            notifyItemChanged(index)
            changeListener(index)
        }
    }

    fun change(oldItem: ViewData, newItem: ViewData) = with(postModels) {
        if (contains(oldItem)) {
            val index = indexOf(oldItem)
            removeAt(index)
            add(index, newItem)
            notifyItemChanged(index)
            changeListener(index)
        }
    }

    fun clear() = with(postModels) {
        val size = size
        clear()
        notifyItemRangeRemoved(0, size)
    }

    fun clearAfter(index: Int) = with(postModels) {
        val size = size
        subList(index, size).clear()
        notifyItemRangeRemoved(index, size)
    }

    fun getPost(position: Int): ViewData {
        return postModels[position]
    }
}