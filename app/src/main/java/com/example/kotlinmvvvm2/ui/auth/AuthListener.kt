package com.example.kotlinmvvvm2.ui.auth

import androidx.lifecycle.LiveData
import com.example.kotlinmvvvm2.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}