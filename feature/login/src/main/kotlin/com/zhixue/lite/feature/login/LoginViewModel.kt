package com.zhixue.lite.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhixue.lite.core.data.repository.UserRepository
import com.zhixue.lite.core.domain.EncryptPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val encryptPasswordUseCase: EncryptPasswordUseCase
) : ViewModel() {

    var message: String by mutableStateOf("")
        private set

    fun login(
        username: String,
        password: String,
        captcha: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            runCatching {
                userRepository.userLogin(
                    username = username,
                    password = encryptPasswordUseCase(password.reversed()),
                    captcha = captcha
                )
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