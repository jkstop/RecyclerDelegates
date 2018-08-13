package fm.finch.recyclerdelegate.delegate

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class PostAdapter(
        vararg delegates: AdapterDelegate
) : RecyclerView.Adapter<BaseViewHolder>(){

    protected val postModels = mutableListOf<PostModel>()
    private val delegateManager = AdapterDelegateManager()

    init {
        delegates.forEach {
            delegateManager.addDelegate(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
            = delegateManager.getDelegate(viewType).onCreateViewHolder(parent)

    override fun getItemCount(): Int
            = postModels.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int)
            = holder.bind(postModels[position])

    override fun getItemViewType(position: Int): Int
            = delegateManager.getItemViewType(postModels[position])

    fun add(input: PostModel) = with(postModels){
        add(input)
        notifyItemInserted(size)
    }

    fun add(input: List<PostModel>) = with(postModels){
        val currentSize = size
        addAll(input)
        notifyItemRangeInserted(currentSize, size)
    }

    fun replace(input: List<PostModel>) = with(postModels){
        val old = postModels.toList()
        clear()
        addAll(input)

        DiffUtil
                .calculateDiff(DiffCallback(old, postModels), true)
                .dispatchUpdatesTo(this@PostAdapter)
    }

    fun remove(input: PostModel) = with(postModels){
        val index = indexOf(input)
        removeAt(index)
        notifyItemRemoved(index)
    }

    fun change(input: PostModel) = with(postModels){
        if (contains(input)){
            val index = indexOf(input)
            removeAt(index)
            add(index, input)
            notifyItemChanged(index)
        }
    }

    fun clear() = with(postModels){
        val size = size
        clear()
        notifyItemRangeRemoved(0, size)
    }

    fun clearAfter(index: Int) = with(postModels){
        val size = size
        subList(index, size).clear()
        notifyItemRangeRemoved(index, size)
    }
}