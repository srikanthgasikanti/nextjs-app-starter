package com.example.simpleapp.data.repository

import com.example.simpleapp.data.remote.api.UserApi
import com.example.simpleapp.domain.model.User
import com.example.simpleapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { userDto ->
            User(
                id = userDto.id,
                name = userDto.name,
                email = userDto.email,
                username = userDto.username
            )
        }
    }
}
