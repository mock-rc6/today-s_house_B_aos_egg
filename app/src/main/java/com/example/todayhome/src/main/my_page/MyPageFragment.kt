package com.example.todayhome.src.main.my_page


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todayhome.R
import com.example.todayhome.databinding.FragmentMyPageBinding
import com.example.todayhome.src.main.my_page.adapter.MyPageTabLayoutViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyPageFragment : Fragment(R.layout.fragment_my_page) {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        tabLayoutViewPage2()


        return binding.root
    }

    private fun tabLayoutViewPage2() {
        val adapter = MyPageTabLayoutViewPagerAdapter(this)
        binding.viewPager2.adapter = adapter

        val tabName = arrayOf("프로필", "나의 쇼핑", )

        binding.settingButton.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabName[position]
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })




    }

}

