package com.nft.signdoc.ui.sign.fragments

import android.os.Bundle
import android.view.View
import com.nft.signdoc.R
import com.nft.signdoc.databinding.FragmentSendBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment

class SendFragment :BaseFragment(R.layout.fragment_send) {

    private val binding by viewBinding(FragmentSendBinding :: bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}