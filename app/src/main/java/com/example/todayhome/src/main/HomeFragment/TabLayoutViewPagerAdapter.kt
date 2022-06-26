package com.example.todayhome.src.main.HomeFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val NUM_PAGES = 7

class TabLayoutViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> FollowingFragment()
            2 -> PhotoFragment()
            3 -> HousesFragment()
            4 -> KnowHowFragment()
            5 -> ExpertFragment()
            6 -> QuestionsFragment()
            else -> PopularFragment()
        }
    }
}
