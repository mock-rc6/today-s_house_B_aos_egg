package com.example.todayhome.src.main.my_page

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todayhome.databinding.ActivitySettingBinding
import com.example.todayhome.src.login.LoginActivity


class SettingActivity : AppCompatActivity() {

    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.passwordChangeTextView.setOnClickListener {
            val intent = Intent(this, passwordChangeActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}