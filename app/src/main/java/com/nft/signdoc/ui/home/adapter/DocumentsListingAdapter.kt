package com.nft.signdoc.ui.home.adapter

import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nft.signdoc.data.networks.response.DocumentItem
import com.nft.signdoc.data.preference.SharePrefs
import com.nft.signdoc.databinding.ItemDocumentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseRecyclerViewAdapter
import com.nft.signdoc.util.Helpers

class DocumentsListingAdapter (val sharePrefs: SharePrefs) : BaseRecyclerViewAdapter<DocumentItem,DocumentsListingAdapter.ItemDocumentViewHolder>() {

    override fun onBindViewHolder(holder: ItemDocumentViewHolder, position: Int) {
        val item = getItemAtPosition(position) ?: return
        holder.bind(item,sharePrefs)
    }

    class ItemDocumentViewHolder(
        private val binding: ItemDocumentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DocumentItem,sharePrefs: SharePrefs) {
            binding.docNameTxt.text = data.title
            binding.docStatusTagTxt.text = data.status
            binding.updatedDateTxt.text = Helpers.getDocumentUpdatedDate(data.updated)
            binding.docSenderNameTxt.text = sharePrefs.walletName
        }
    }

    override fun createViewHolderInternal(parent: ViewGroup, viewType: Int): ItemDocumentViewHolder {
        return ItemDocumentViewHolder(
            parent.viewBinding(ItemDocumentBinding::inflate)
        )
    }
}