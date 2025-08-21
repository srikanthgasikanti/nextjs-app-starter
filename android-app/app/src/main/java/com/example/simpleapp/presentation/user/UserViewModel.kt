package com.example.simpleapp.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapp.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()
    
    init {
        loadUsers()
    }
    
    private fun loadUsers() {
        viewModelScope.launch {
            try {
                val users = getUsersUseCase()
                _uiState.value = UserUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class UserUiState {
    data object Loading : UserUiState()
    data class Success(val users: List<com.example.simpleapp.domain.model.User>) : UserUiState()
    data class Error(val message: String) : UserUiState()
}
