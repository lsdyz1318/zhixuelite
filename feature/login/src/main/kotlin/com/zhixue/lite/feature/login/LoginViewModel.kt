package com.zhixue.lite.feature.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhixue.lite.core.data.repository.UserRepository
import com.zhixue.lite.core.domain.EncryptPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val encryptPasswordUseCase: EncryptPasswordUseCase
) : ViewModel() {

    val username: TextFieldState = TextFieldState()
    val password: TextFieldState = TextFieldState()

    var message: String by mutableStateOf("")
        private set

    private val _isUsernameValid: Flow<Boolean> =
        snapshotFlow { username.text }.map { it.length >= 6 }

    private val _isPasswordValid: Flow<Boolean> =
        snapshotFlow { password.text }.map { it.length >= 6 }

    val uiState: StateFlow<LoginUiState> =
        combine(_isUsernameValid, _isPasswordValid) { isUsernameValid, isPasswordValid ->
            LoginUiState(isFormsValid = isUsernameValid && isPasswordValid)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LoginUiState()
        )

    fun login(
        captcha: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            runCatching {
                val username = username.text.toString()
                val password = encryptPasswordUseCase(password.text.reversed().toString())
                userRepository.userLogin(username, password, captcha)
            }.onSuccess {
                onSuccess()
            }.onFailure {
                message = it.message.orEmpty()
            }
        }
    }

    fun onMessageShown() {
        message = ""
    }
}

data class LoginUiState(
    val isFormsValid: Boolean = false
)