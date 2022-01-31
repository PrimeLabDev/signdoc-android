package com.nft.signdoc.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbiomatric.AllDocumentsFragment
import com.example.testbiomatric.SharedWithMeDocumentsFragment

class ViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> AllDocumentsFragment()
            1 -> SharedWithMeDocumentsFragment()
            else -> AllDocumentsFragment()
        }
    }
}