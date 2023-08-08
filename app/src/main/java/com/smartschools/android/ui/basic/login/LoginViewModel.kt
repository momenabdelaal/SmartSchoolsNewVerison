package com.smartschools.android.ui.basic.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.smartschools.android.data.model.auth.LoginResponse
import com.smartschools.android.domain.usecase.UserUseCase
import com.smartschools.android.domain.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useUseCase: UserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState>
        get() = _uiState

    var rememberMe = false


    fun userLogin(userName: String, pass: String, type: String) {
        val jsonRequest = JsonObject()
        jsonRequest.addProperty("username",userName)
        jsonRequest.addProperty("password",pass)
        jsonRequest.addProperty("type",type)

        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = useUseCase.userLogin(jsonRequest)
            _uiState.value = when (result) {
                is Result.Loading -> UiState.Loading
                is Result.Error -> ({

                    Log.d("test", "login: "+result.errorType)

                }) as UiState
                is Result.Success -> {
                    UiState.Success(result.data!!)

                }
            }
        }
    }


    //used to reset state after navigation
    fun navigateToConfirmLoginFragment() {
        _uiState.value = UiState.Idle
    }

    sealed class UiState {
        object Loading : UiState()
        object Idle : UiState()
        class Error(val errorMsg: String) : UiState()
        class Success(val data: LoginResponse) : UiState()
    }

}