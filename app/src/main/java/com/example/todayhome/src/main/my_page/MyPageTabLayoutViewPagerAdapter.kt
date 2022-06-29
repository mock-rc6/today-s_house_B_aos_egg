package com.example.todayhome.src.main.my_page

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val NUM_PAGES = 2

class MyPageTabLayoutViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyPageProfileFragment()
            1 -> MyPageMyShoppingFragment()
            else -> MyPageProfileFragment()
        }
    }
}
