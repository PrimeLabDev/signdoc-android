package com.nft.signdoc.ui.auth

import android.os.Bundle
import android.view.View
import com.nft.signdoc.R
import com.nft.signdoc.databinding.FragmentOtpBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint



class OtpFragment : BaseFragment(R.layout.fragment_otp) {

    private val binding by viewBinding(FragmentOtpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}