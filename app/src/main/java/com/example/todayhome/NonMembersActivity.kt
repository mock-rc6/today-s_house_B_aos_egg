package com.example.todayhome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.todayhome.databinding.ActivityEmailJoinBinding
import com.example.todayhome.databinding.ActivityNonMembersBinding

class NonMembersActivity : AppCompatActivity() {

    private val binding: ActivityNonMembersBinding by lazy {
        ActivityNonMembersBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var emailArray: Array<String> = arrayOf(
            "naver.com",
            "hanmail.net",
            "daum.net",
            "gmail.com",
            "nate.com",
            "hotmail.com",
            "outlook.com",
            "icloud.com",
            "직접입력"
        )

        binding.emailListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("선택해주세요.")
                .setItems(
                    emailArray
                ) { _, which ->
                    binding.emailListButton.text= emailArray[which].lowercase()
                    binding.emailListButton.text.toString().forEach {
                        it.lowercase()
                    }
                }
            builder.show()
        }

        binding.emailLoginArrowButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

    }
}