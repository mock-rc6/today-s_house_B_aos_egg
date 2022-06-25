package com.example.todayhome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayhome.databinding.ActivityEmailJoinBinding
import com.example.todayhome.databinding.ActivityEmailLoginBinding

class Email_join_Activity : AppCompatActivity() {

    private val binding: ActivityEmailJoinBinding by lazy {
        ActivityEmailJoinBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.emailLoginArrowButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}