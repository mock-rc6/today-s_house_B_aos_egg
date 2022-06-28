package com.example.todayhome.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todayhome.config.*
import com.example.todayhome.databinding.ActivityEmailJoinBinding

class Email_join_Activity : AppCompatActivity(), SignUpView {

    private val binding: ActivityEmailJoinBinding by lazy {
        ActivityEmailJoinBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.emailLoginArrowButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener {
            signUp()

        }


    }

    private fun getUser(): User {
        val email: String = binding.editEmail.text.toString()
        val pwd: String = binding.editPassword.text.toString()
        val name: String = binding.editNickname.text.toString()
        val gender: String = binding.gender.text.toString()
        val bornYear: Int = binding.bornYear.text.toString().toInt()
        return User(email, pwd, name,gender,bornYear)
    }

    private fun signUp() {
        if (binding.editEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }



        if (binding.editPassword.text.toString() != binding.editPassword2.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.editNickname.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }




        Log.d("Email_join_Activity", "성공")

        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())

    }

    override fun onSignUpSuccess() {
        finish()
    }


    override fun onSignUpFailure() {

    }


}

