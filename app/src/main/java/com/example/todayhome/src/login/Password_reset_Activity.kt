package com.example.todayhome.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayhome.databinding.ActivityPasswordResetBinding

class Password_reset_Activity : AppCompatActivity() {
    private val binding: ActivityPasswordResetBinding by lazy {
        ActivityPasswordResetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.passwordRestArrowButton.setOnClickListener {
            val intent = Intent(this, Email_loginActivity::class.java)
            startActivity(intent)
        }
    }
}