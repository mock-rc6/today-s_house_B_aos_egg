package com.example.todayhome.src.main.store.adapter

import android.content.ClipData
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todayhome.src.main.MainActivity
import com.example.todayhome.src.main.store.DetailPopularActivity
import com.example.todayhome.src.main.store.fragment.*


private const val NUM_PAGES = 5

class ItemDetailAdapter(activity: DetailPopularActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> item_infoviewFragment()
            1 -> ReviewFragment()
            2 -> inquiryrFragment()
            3 -> Delivery2Fragment()
            4 -> suggesFragment()
            else -> item_infoviewFragment()
        }
    }
}
