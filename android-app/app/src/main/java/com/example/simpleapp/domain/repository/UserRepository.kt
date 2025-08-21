package com.example.simpleapp.domain.repository

import com.example.simpleapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
