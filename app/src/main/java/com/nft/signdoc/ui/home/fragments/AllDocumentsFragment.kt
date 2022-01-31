package com.example.testbiomatric

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nft.signdoc.R
import com.nft.signdoc.databinding.AllDocumentsFragmentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import com.nft.signdoc.ui.home.adapter.DocumentsListingAdapter

class AllDocumentsFragment : BaseFragment(R.layout.all_documents_fragment){


    private val binding by viewBinding(AllDocumentsFragmentBinding::bind)

    private val documentsListingAdapter by lazy {
        DocumentsListingAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initAdapter()
    }

    private fun initListeners(){

    }

    private fun initAdapter() {
        with(binding.recyclerView) {
            adapter = documentsListingAdapter
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        documentsListingAdapter.notifyDataSetChanged()
    }


}