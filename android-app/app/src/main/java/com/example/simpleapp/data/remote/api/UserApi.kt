package com.example.simpleapp.data.remote.api

import com.example.simpleapp.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
