package com.example.homepagesearchandfilter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homepagesearchandfilter.ui.fragments.mapitemfragments.AllFragment
import com.example.homepagesearchandfilter.ui.fragments.mapitemfragments.HairFragment
import com.example.homepagesearchandfilter.ui.fragments.mapitemfragments.NailsCareFragment
import com.example.homepagesearchandfilter.ui.fragments.mapitemfragments.ShampoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllFragment()
            }
            1 -> {
                HairFragment()
            }
            2 -> {
                ShampoFragment()
            }
            3 -> {
                NailsCareFragment()
            }
            4 -> {
                AllFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}