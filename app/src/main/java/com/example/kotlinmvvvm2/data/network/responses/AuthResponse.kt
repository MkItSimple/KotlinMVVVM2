package com.example.kotlinmvvvm2.data.network.responses

import com.example.kotlinmvvvm2.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)