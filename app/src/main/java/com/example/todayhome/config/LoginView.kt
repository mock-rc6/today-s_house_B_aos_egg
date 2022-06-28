package com.example.todayhome.config




interface LoginView {
    fun onLoginSuccess(code : Int, result : Result)
    fun onLoginFailure()
}