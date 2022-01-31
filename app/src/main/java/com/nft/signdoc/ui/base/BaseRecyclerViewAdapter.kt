package com.nft.signdoc.ui.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter< VH : RecyclerView.ViewHolder>(

) : RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolderInternal(parent, viewType)
    }

    override fun getItemCount(): Int = 3

    abstract fun createViewHolderInternal(parent: ViewGroup, viewType: Int): VH

}