package com.example.testbiomatric

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nft.signdoc.R
import com.nft.signdoc.databinding.AllDocumentsFragmentBinding
import com.nft.signdoc.extensions.observeResultFlow
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import com.nft.signdoc.ui.home.adapter.DocumentsListingAdapter
import com.nft.signdoc.viewmodel.UserViewModel

class AllDocumentsFragment : BaseFragment(R.layout.all_documents_fragment) {


    private val binding by viewBinding(AllDocumentsFragmentBinding::bind)
    private val userViewModel: UserViewModel by viewModels()


    private val documentsListingAdapter by lazy {
        DocumentsListingAdapter(userViewModel.prefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initAdapter()
    }

    private fun initListeners() {
        observeResultFlow(
            userViewModel.getAllDocuments(

            ), successHandler = {
                documentsListingAdapter.setData(it)
                binding.noRecordTxt.visibility = if (it.isNullOrEmpty())
                    View.VISIBLE
                else
                    View.GONE

            }, errorHandler = {
                Toast.makeText(activity, it?.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }, httpErrorHandler = {
                Toast.makeText(activity, it?.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun initAdapter() {
        with(binding.recyclerView) {
            adapter = documentsListingAdapter
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        documentsListingAdapter.notifyDataSetChanged()
    }


}