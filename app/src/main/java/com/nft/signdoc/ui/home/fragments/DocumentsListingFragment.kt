package com.nft.signdoc.ui.home.fragments

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.nft.signdoc.R
import com.nft.signdoc.databinding.DocumentsListingFragmentBinding
import com.nft.signdoc.databinding.HomeFragmentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import com.nft.signdoc.ui.home.adapter.ViewPagerAdapter

class DocumentsListingFragment : BaseFragment(R.layout.documents_listing_fragment) {

    private val binding by viewBinding(DocumentsListingFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        initListeners()

    }

    private fun setupViewPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->

            tab.text = when (position) {
                0 -> "All Documents"
                1 -> "Shared with me"
                else -> "All Documents"
            }
        }.attach()
    }

    private fun initListeners() {

    }


}