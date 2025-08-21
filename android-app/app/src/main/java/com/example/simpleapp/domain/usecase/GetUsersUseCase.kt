package com.example.simpleapp.domain.usecase

import com.example.simpleapp.domain.model.User
import com.example.simpleapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}
