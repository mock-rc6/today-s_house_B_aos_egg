package com.example.todayhome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ourtable.recipe.RecipeFragment
import com.example.ourtable.store.StoreFragment
import com.example.ourtable.style.StyleFragment
import com.example.todayhome.HomeFragment.HomeFragment
import com.example.todayhome.databinding.ActivityMainBinding
import com.example.todayhome.my_page.MyPageFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val home = HomeFragment()
        val myPage = MyPageFragment()
        val recipe = RecipeFragment()
        val store = StoreFragment()
        val style = StyleFragment()

        replaceFragment(home)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(home)
                R.id.my_page -> replaceFragment(myPage)
                R.id.home_service -> replaceFragment(recipe)
                R.id.store -> replaceFragment(store)
                R.id.plus -> replaceFragment(style)

            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

}