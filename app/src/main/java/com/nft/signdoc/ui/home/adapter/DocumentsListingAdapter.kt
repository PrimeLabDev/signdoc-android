package com.nft.signdoc.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nft.signdoc.data.networks.response.DocumentItem
import com.nft.signdoc.databinding.ItemDocumentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseRecyclerViewAdapter

class DocumentsListingAdapter () : BaseRecyclerViewAdapter<DocumentItem,DocumentsListingAdapter.ItemDocumentViewHolder>() {

    override fun onBindViewHolder(holder: ItemDocumentViewHolder, position: Int) {
        val item = getItemAtPosition(position) ?: return
        holder.bind(item)
    }

    class ItemDocumentViewHolder(
        private val binding: ItemDocumentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DocumentItem) {
//            binding.textWalletName.text = data.name
//            binding.imageSelected.visibility = if (data.selected) View.VISIBLE else View.GONE
//            binding.root.setDebouncedClickListener { onItemClicked?.invoke(data) }
            binding.docNameTxt.text = data.title
            binding.docStatusTagTxt.text = data.status

        }
    }

    override fun createViewHolderInternal(parent: ViewGroup, viewType: Int): ItemDocumentViewHolder {
        return ItemDocumentViewHolder(
            parent.viewBinding(ItemDocumentBinding::inflate)
        )
    }
}