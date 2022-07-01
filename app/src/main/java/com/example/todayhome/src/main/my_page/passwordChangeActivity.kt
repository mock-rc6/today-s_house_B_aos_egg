package com.example.todayhome.src.main.my_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todayhome.R
import com.example.todayhome.config.*
import com.example.todayhome.databinding.ActivityPasswordChangeBinding
import com.example.todayhome.databinding.ActivitySettingBinding

class passwordChangeActivity : AppCompatActivity(),ChangeView {

    private val binding: ActivityPasswordChangeBinding by lazy {
        ActivityPasswordChangeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.completionButton.setOnClickListener {
            change()
        }
        setContentView(binding.root)
    }

    private fun saveJwt2(jwt: String) {
        val spf = getSharedPreferences("auth2" , MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun change() {
        if (binding.editText.text.toString() != binding.editText2.text.toString()) {
            Toast.makeText(this,"비밀번호가 일치하지않음",Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setChangeView(this)
        authService.change(UserPassword(""))
        Log.d("passwordChangeActivity", "성공")
    }

    override fun onSignUpSuccess(code: Int) {
        when(code){
            1000 ->{
                finish()
            }
        }
    }

    override fun onSignUpFailure() {
        Log.d("passwordChangeActivity2", "실패")
    }


}