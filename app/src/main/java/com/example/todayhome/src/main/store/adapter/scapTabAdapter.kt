package com.example.todayhome.src.main.store.adapter

import android.content.ClipData
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todayhome.src.main.MainActivity
import com.example.todayhome.src.main.store.DetailPopularActivity
import com.example.todayhome.src.main.store.fragment.*
import com.example.todayhome.src.main.store.scapbookActivity


private const val NUM_PAGES = 3

class scapTabAdapter(activity: scapbookActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScapAllTabFragment()
            1 -> ScapFolderFragment()
            2 -> ScapItemFragment()
            else -> ScapAllTabFragment()
        }
    }
}
