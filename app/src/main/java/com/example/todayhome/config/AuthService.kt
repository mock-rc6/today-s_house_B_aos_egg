package com.example.todayhome.config

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    private lateinit var changeView: ChangeView

    private var number:Long =0


    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setChangeView(changeView: ChangeView){
        this.changeView = changeView
    }

    fun signUp(user: User) {

        val signUpService = getRetrofit().create(RetrofitLogin::class.java)

        signUpService.signUp(user).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful && response.code() == 200) {
                    val signUpResponse: ResponseLogin = response.body()!!

                    Log.d("SIGNUP-RESPONSE", signUpResponse.toString())
                    Log.d("코난", signUpResponse.code.toString())
                    Log.d("코난", user.password)
                    Log.d("코난2", signUpResponse.result?.userIdx.toString())


                    when (signUpResponse.code) {

                        1000 -> signUpView.onSignUpSuccess()
                        else -> signUpView.onSignUpFailure()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                //실패처리
            }
        })
    }


    fun login(user: User) {
        val loginService = getRetrofit().create(RetrofitLogin::class.java)


        loginService.login(user).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful && response.code() == 200) {

                    val loginResponse: ResponseLogin = response.body()!!
                    Log.d("cccc", loginResponse.toString())
                    number= loginResponse.result?.userIdx.toString().toLong()
                    Log.d("코난 로그인", loginResponse.result?.userIdx.toString().toLong().toString())
                    when (val code = loginResponse.code) {
                        1000 -> loginView.onLoginSuccess(code, loginResponse.result!!)
                        else -> loginView.onLoginFailure()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                //실패처리
            }
        })
    }

    fun change(user: UserPassword) {
        val loginService = getRetrofit().create(RetrofitLogin::class.java)


        loginService.change(number,user).enqueue(object : Callback<ResponseLogin2> {
            override fun onResponse(call: Call<ResponseLogin2>, response: Response<ResponseLogin2>) {
                if (response.isSuccessful && response.code() == 200) {

                    val changeResponse: ResponseLogin2 = response.body()!!
                    Log.d("코난3", changeResponse.code.toString())

                    when (val code = changeResponse.code) {
                        1000 -> changeView.onSignUpSuccess(code)
                        else -> changeView.onSignUpFailure()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin2>, t: Throwable) {
                //실패처리
            }
        })
    }
}