package com.example.testbiomatric

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nft.signdoc.R
import com.nft.signdoc.databinding.HomeFragmentBinding
import com.nft.signdoc.databinding.SharedWithMeDocumentsFragmentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import com.nft.signdoc.ui.home.adapter.DocumentsListingAdapter
import com.nft.signdoc.viewmodel.UserViewModel

class SharedWithMeDocumentsFragment : BaseFragment(R.layout.shared_with_me_documents_fragment){

    private val binding by viewBinding(SharedWithMeDocumentsFragmentBinding::bind)
    private val userViewModel: UserViewModel by viewModels()


    private val documentsListingAdapter by lazy {
        DocumentsListingAdapter(userViewModel.prefs)
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
    }

}