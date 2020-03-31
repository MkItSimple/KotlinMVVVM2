package com.example.kotlinmvvvm2.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvvm2.data.repositories.UserRepository

class AuthViewModel : ViewModel(){
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        // success
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}