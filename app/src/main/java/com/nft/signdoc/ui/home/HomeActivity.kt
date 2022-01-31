package com.nft.signdoc.ui.home;

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.core.content.ContextCompat

import com.nft.signdoc.R;
import com.nft.signdoc.databinding.ActivityHomeBinding
import com.nft.signdoc.databinding.ActivityLoginBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.ui.home.fragments.DocumentsListingFragment
import com.nft.signdoc.ui.home.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity(){

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        listenToViewEvents()
    }

    private fun initViews(){
        val fragment = HomeFragment()
        showFragment(fragment)
        binding.homeTabIv.isClickable = false
        binding.documentTabIv.isClickable = true
    }

    private fun listenToViewEvents() {
        binding.homeTabIv.setOnClickListener {
            binding.homeTabIv.isClickable = false
            binding.documentTabIv.isClickable = true
            binding.homeTabIv.background = ContextCompat.getDrawable(this, R.drawable.selected_home_option_bg)
            binding.homeTabIv.setImageResource(R.drawable.home_selected_ic)
            binding.documentTabIv.setBackgroundResource(0)
            binding.documentTabIv.setImageResource(R.drawable.document_non_selected_ic)
            val fragment = HomeFragment()
            showFragment(fragment)
        }

        binding.documentTabIv.setOnClickListener {
            binding.homeTabIv.isClickable = true
            binding.documentTabIv.isClickable = false
            binding.documentTabIv.background = ContextCompat.getDrawable(this, R.drawable.selected_home_option_bg)
            binding.documentTabIv.setImageResource(R.drawable.document_selected_ic)
            binding.homeTabIv.setBackgroundResource(0)
            binding.homeTabIv.setImageResource(R.drawable.home_non_selected_ic)
            val fragment = DocumentsListingFragment()
            showFragment(fragment)
        }

    }
}