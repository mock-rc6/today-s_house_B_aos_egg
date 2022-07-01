package com.example.todayhome.src.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todayhome.config.*
import com.example.todayhome.databinding.ActivityEmailJoinBinding


class Email_join_Activity : AppCompatActivity() , SignUpView{

    lateinit var binding:  ActivityEmailJoinBinding


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





    }

    private fun getUser(): User {
        val email: String = binding.editEmail.text.toString()
        val pwd: String = binding.editPassword.text.toString()
        val name: String = binding.editNickname.text.toString()
        val gender = "male"
        val bornYear:Int= 1999
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


}

