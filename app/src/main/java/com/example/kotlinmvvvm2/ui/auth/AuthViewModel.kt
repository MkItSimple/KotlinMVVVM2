package com.example.kotlinmvvvm2.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvvm2.data.repositories.UserRepository
import com.example.kotlinmvvvm2.util.ApiException
import com.example.kotlinmvvvm2.util.Coroutines
import com.example.kotlinmvvvm2.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        // success
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch(e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}