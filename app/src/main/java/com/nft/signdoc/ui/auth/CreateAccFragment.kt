package com.nft.signdoc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nft.signdoc.R
import com.nft.signdoc.databinding.FragmentCreateAccountBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccFragment : BaseFragment(R.layout.fragment_create_account) {

    private val binding by viewBinding(FragmentCreateAccountBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}