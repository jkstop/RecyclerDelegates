package fm.finch.recyclerdelegate.delegate

import android.support.v4.util.SparseArrayCompat

class AdapterDelegateManager{

    private var delegates: SparseArrayCompat<AdapterDelegate> = SparseArrayCompat()

    fun addDelegate(delegate: AdapterDelegate) = delegates.put(delegates.size(), delegate)

    fun getDelegate(viewType: Int): AdapterDelegate = delegates[viewType]

    fun getItemViewType(postModel: PostModel): Int{
        for (i in 0 until delegates.size()){
            if (delegates[i].isValidType(postModel)){
                return delegates.keyAt(i)
            }
        }

        throw NullPointerException("Delegate not found for $postModel")
    }
}