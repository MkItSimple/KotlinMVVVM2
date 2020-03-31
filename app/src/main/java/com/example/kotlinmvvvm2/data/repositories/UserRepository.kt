package com.example.kotlinmvvvm2.data.repositories

import com.example.kotlinmvvvm2.data.db.AppDatabase
import com.example.kotlinmvvvm2.data.db.entities.User
import com.example.kotlinmvvvm2.data.network.MyApi
import com.example.kotlinmvvvm2.data.network.SafeApiRequest
import com.example.kotlinmvvvm2.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{ api.userLogin(email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()
}