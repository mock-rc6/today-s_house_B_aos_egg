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
            Log.d("제발",getJwt().toString())
            Log.d("제발",getJwt2().toString())
            finish()
        }

        binding.completionButton.setOnClickListener {
            change()
        }


        setContentView(binding.root)
    }



    private fun change() {
        if (binding.editText.text.toString() != binding.editText2.text.toString()) {
            Toast.makeText(this,"비밀번호가 일치하지않음",Toast.LENGTH_SHORT).show()
            return
        }

        val password = binding.editText2.text.toString()
        val authService = AuthService()
        authService.setChangeView(this)
        authService.change(UserPassword(password),getJwt().toString(),getJwt2())
        Log.d("passwordChangeActivity", "성공")
    }



    override fun onSignUpSuccess(code: Int) {
        when(code){
            1000 ->{
                Log.d("passwordChangeActivity2", "성공이요")
                finish()
            }
        }
    }

    override fun onSignUpFailure() {
        Log.d("passwordChangeActivity2", "실패")
    }



    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth2" , AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    private fun getJwt2(): Long {
        val spf = this.getSharedPreferences("auth3" , AppCompatActivity.MODE_PRIVATE)

        return spf.getLong("jwt", 0)
    }


}