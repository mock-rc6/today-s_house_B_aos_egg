package com.example.todayhome.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todayhome.config.*
import com.example.todayhome.databinding.ActivityEmailLoginBinding
import com.example.todayhome.src.main.MainActivity



class Email_loginActivity : AppCompatActivity(), LoginView {

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

        binding.loginButton.setOnClickListener {
            login()
        }


    }

    private fun login() {
        if (binding.editEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.editPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()


        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(email, password, "", ))


    }


    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginSuccess(code : Int , result: Result) {
        when(code) {
            1000 -> {
                saveJwt2(result.jwt)
                startMainActivity()

            }
        }
    }

    override fun onLoginFailure() {
        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
    }
}


