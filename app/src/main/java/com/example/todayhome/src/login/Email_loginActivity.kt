package com.example.todayhome.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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

        binding.editEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.editEmail.length() > 1 && binding.editPassword.length()> 1) { // 패스워드의 길이가 1미만이면
                    binding.loginButton.isClickable = true
                    binding.loginButton.isEnabled = true
                } else {
                    binding.loginButton.isClickable = false
                    binding.loginButton.isEnabled = false
                }

            }
        })

        binding.editPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.editEmail.length() > 0 && binding.editPassword.length()> 0) {
                    binding.loginButton.isClickable = true
                    binding.loginButton.isEnabled = true
                } else {
                    binding.loginButton.isClickable = false
                    binding.loginButton.isEnabled = false
                }

            }
        })


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

    private fun saveJwt3(jwt: Long) {
        val spf = getSharedPreferences("auth3" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putLong("jwt", jwt)
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
                saveJwt3(result.userIdx)
                startMainActivity()

            }
        }
    }

    override fun onLoginFailure() {
        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
    }
}


