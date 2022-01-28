package com.nft.signdoc.ui.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nft.signdoc.R
import com.nft.signdoc.databinding.ActivityDocMainBinding
import com.nft.signdoc.databinding.ActivityOtpBinding
import com.nft.signdoc.extensions.viewBinding
import com.nft.signdoc.ui.auth.SignupActivity
import com.nft.signdoc.ui.base.BaseActivity
import com.nft.signdoc.ui.sign.fragments.SendFragment
import com.nft.signdoc.ui.sign.fragments.SignFragment
import com.nft.signdoc.ui.sign.fragments.UploadFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocSignActivity : BaseActivity() {

    private val binding by viewBinding(ActivityDocMainBinding::inflate)

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, DocSignActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        binding.pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 3

        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> UploadFragment()
                1 -> SignFragment()
                2 -> SendFragment()
                else -> Fragment()
            }
    }
}