package com.example.todayhome.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment
import com.example.ourtable.recipe.RecipeFragment
import com.example.ourtable.store.StoreFragment
import com.example.ourtable.style.StyleFragment
import com.example.todayhome.src.main.HomeFragment.HomeFragment
import com.example.todayhome.R
import com.example.todayhome.databinding.ActivityMainBinding
import com.example.todayhome.src.main.my_page.MyPageFragment
import com.facebook.CallbackManager
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var callbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val home = HomeFragment()
        val myPage = MyPageFragment()
        val recipe = RecipeFragment()
        val store = StoreFragment()
        val style = StyleFragment()

        val bottomSheetView = layoutInflater.inflate(R.layout.activity_dialog, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)
        replaceFragment(home)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(home)
                R.id.my_page -> replaceFragment(myPage)
                R.id.home_service -> replaceFragment(recipe)
                R.id.store -> replaceFragment(store)
                R.id.plus -> bottomSheetDialog.show()

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