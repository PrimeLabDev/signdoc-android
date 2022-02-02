package com.nft.signdoc.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nft.signdoc.databinding.ItemDocumentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseRecyclerViewAdapter

class DocumentsListingAdapter () : BaseRecyclerViewAdapter<DocumentsListingAdapter.ItemDocumentViewHolder>() {

    override fun onBindViewHolder(holder: ItemDocumentViewHolder, position: Int) {


    }

    class ItemDocumentViewHolder(
        private val binding: ItemDocumentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun createViewHolderInternal(parent: ViewGroup, viewType: Int): ItemDocumentViewHolder {
        return ItemDocumentViewHolder(
            parent.viewBinding(ItemDocumentBinding::inflate)
        )
    }
}