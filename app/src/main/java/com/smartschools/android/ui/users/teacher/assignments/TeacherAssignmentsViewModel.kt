package com.smartschools.android.ui.users.teacher.assignments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartschools.android.data.model.dashboard.DashboardResponse
import com.smartschools.android.domain.network.ErrorType
import com.smartschools.android.domain.usecase.UserUseCase
import com.smartschools.android.domain.network.Result
import com.smartschools.android.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherAssignmentsViewModel @Inject constructor(private val useUseCase: UserUseCase) : ViewModel() {

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

                   UiState.Error(result.errorType)

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