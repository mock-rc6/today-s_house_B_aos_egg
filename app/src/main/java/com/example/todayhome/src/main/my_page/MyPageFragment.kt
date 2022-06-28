package com.example.todayhome.src.main.my_page


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todayhome.R
import com.example.todayhome.databinding.FragmentMyPageBinding
import com.example.todayhome.databinding.FragmentStoreBinding
import com.example.todayhome.src.login.LoginActivity
import com.example.todayhome.src.main.MainActivity


class MyPageFragment : Fragment(R.layout.fragment_my_page) {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)



        return binding.root
    }


    private fun getJwt(): Int {
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("jwt", 0)
    }

    override fun onStart() {
        super.onStart()

        initViews()
    }

    private fun initViews() {
        val jwt: Int = getJwt()

        if (jwt == 0) {
            binding.lockerLoginTv.text = "로그인"

            binding.lockerLoginTv.setOnClickListener {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        } else {
            binding.lockerLoginTv.text = "로그아웃"

            binding.lockerLoginTv.setOnClickListener {
                logout()
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
    }


    private fun logout() {
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf!!.edit()

        editor.remove("jwt")
        editor.apply()
    }


}

