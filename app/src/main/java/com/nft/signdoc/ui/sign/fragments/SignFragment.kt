package com.nft.signdoc.ui.sign.fragments

import android.os.Bundle
import android.view.View
import com.nft.signdoc.R
import com.nft.signdoc.databinding.FragmentSignBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment

class SignFragment : BaseFragment(R.layout.fragment_sign) {

    private val binding by viewBinding(FragmentSignBinding :: bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}