package com.nft.signdoc.ui.auth

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nft.signdoc.R
import com.nft.signdoc.databinding.ActivityCreateAccountBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccActivity : BaseActivity() {

    private val binding by viewBinding(ActivityCreateAccountBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listenToViewEvents()
    }

    private fun listenToViewEvents() {

    }
}