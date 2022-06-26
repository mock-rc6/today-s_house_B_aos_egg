package com.example.todayhome.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayhome.databinding.ActivityEmailLoginBinding

class Email_loginActivity : AppCompatActivity() {

    private val binding: ActivityEmailLoginBinding by lazy {
        ActivityEmailLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.passwordReset.setOnClickListener {
            val intent = Intent(this, Password_reset_Activity::class.java)
            startActivity(intent)
        }

        binding.emailLoginArrowButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}