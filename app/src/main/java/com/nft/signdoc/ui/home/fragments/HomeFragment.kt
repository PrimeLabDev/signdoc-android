package com.nft.signdoc.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nft.signdoc.R
import com.nft.signdoc.databinding.HomeFragmentBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseFragment

class HomeFragment : BaseFragment(R.layout.home_fragment) {


    private val binding by viewBinding(HomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners(){

    }


}
