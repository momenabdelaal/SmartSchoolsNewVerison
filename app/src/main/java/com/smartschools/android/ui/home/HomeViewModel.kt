package com.smartschools.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartschools.android.data.model.dashboard.DashboardResponse
import com.smartschools.android.domain.network.ErrorType
import com.smartschools.android.domain.usecase.UserUseCase
import com.smartschools.android.domain.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useUseCase: UserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState>
        get() = _uiState




    fun getDashboard() {


        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = useUseCase.getDashboard()
            _uiState.value = when (result) {
                is Result.Loading -> UiState.Loading
                is Result.Error -> {
                    when (result.errorType) {
                        ErrorType.DataError -> UiState.Error("Data error")
                        ErrorType.NetworkError ->UiState.Error("Network error")
                        ErrorType.ServerError -> UiState.Error("Server error")
                        ErrorType.UnknownError -> UiState.Error("Unknown error")
                        null -> UiState.Error("error happened")
                    }
                }
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
        class Success(val data: DashboardResponse) : UiState()
    }

}