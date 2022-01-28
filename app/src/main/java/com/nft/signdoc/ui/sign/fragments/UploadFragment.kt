package com.nft.signdoc.ui.sign.fragments

import android.os.Bundle
import android.view.View
import com.nft.signdoc.R
import com.nft.signdoc.databinding.FragmentUploadBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment

class UploadFragment : BaseFragment(R.layout.fragment_upload) {

    private val binding by viewBinding(FragmentUploadBinding :: bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}