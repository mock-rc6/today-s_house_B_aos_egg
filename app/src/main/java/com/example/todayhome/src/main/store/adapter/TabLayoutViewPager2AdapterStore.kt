package com.example.todayhome.src.main.store.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todayhome.src.main.HomeFragment.*
import com.example.todayhome.src.main.store.fragment.*


private const val NUM_PAGES = 9

class TabLayoutViewPager2AdapterStore (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StoreHomeFragment()
            1 -> BestFragment()
            2 -> TodaySaleFragment()
            3 -> BigSaleFragment()
            4 -> WeekendFragment()
            5 -> GoodsFragment()
            6 -> FastShippingFragment()
            7 -> PremiumFragment()
            8 ->PlanFragment()
            else -> StoreHomeFragment()
        }
    }
}
