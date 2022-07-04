package com.example.todayhome.src.main.store

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todayhome.src.main.my_page.fragment.MyPageMyShoppingFragment
import com.example.todayhome.src.main.my_page.fragment.MyPageProfileFragment
import com.example.todayhome.src.main.store.fragment.*


private const val NUM_PAGES = 5

class detailPoplurTabLayoutViewPagerAdapter(fragment: DetailPopularActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> item_info()
            1 -> poplurReviewFragment()
            2 ->inquiryFragment()
            3 -> deliveryFragment()
            4-> proposalFragment()
            else -> item_info()
        }
    }
}
