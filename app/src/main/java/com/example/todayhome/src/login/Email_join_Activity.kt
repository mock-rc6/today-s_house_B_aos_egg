package com.example.todayhome.src.login


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todayhome.R
import com.example.todayhome.config.*
import com.example.todayhome.databinding.ActivityEmailJoinBinding
import java.util.regex.Pattern


class Email_join_Activity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivityEmailJoinBinding
    private val emailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.emailLoginArrowButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener {
            signUp()

        }

        binding.allCheckBox.setOnClickListener {

            if (binding.allCheckBox.isChecked) {
                binding.ageCheckBox.isChecked = true
                binding.useCheckBox.isChecked = true
                binding.privacyCheckBox.isChecked = true
                binding.eventCheckBox.isChecked = true
                binding.signUpButton.isEnabled = true
                binding.signUpButton.isClickable = true
            } else {
                binding.ageCheckBox.isChecked = false
                binding.useCheckBox.isChecked = false
                binding.privacyCheckBox.isChecked = false
                binding.eventCheckBox.isChecked = false
                binding.signUpButton.isEnabled = false
                binding.signUpButton.isClickable = false
            }


        }

        binding.editEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })


    }

    private fun getUser(): User {
        val email: String = binding.editEmail.text.toString()
        val pwd: String = binding.editPassword.text.toString()
        val name: String = binding.editNickname.text.toString()

        return User(email, pwd, name)
    }

    private fun signUp() {
        if (binding.editEmail.text.toString().isEmpty()) {

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


        val authService = AuthService()
        authService.setSignUpView(this)
        authService.signUp(getUser())
        Log.d("Email_join_Activity", "성공")
    }

    override fun onSignUpSuccess() {
        finish()
    }


    override fun onSignUpFailure() {
        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
    }

    fun checkEmail(): Boolean {
        var email = binding.editEmail.text.toString().trim()
        val p = Pattern.matches(emailValidation, email)
        if (p) {
            //이메일 형태가 정상일 경우
            binding.loginButton.isEnabled = true
            binding.loginButton.isClickable = true
            return true
        } else {
            binding.loginButton.isEnabled = false
            binding.loginButton.isClickable = false
            return false
        }
    }
}



