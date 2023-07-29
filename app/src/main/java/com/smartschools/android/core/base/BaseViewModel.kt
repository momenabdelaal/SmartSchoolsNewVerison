package com.smartschools.android.core.base

import androidx.lifecycle.ViewModel
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    sharedPreferences: SharedPreferencesImpl
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState>
        get() = _uiState


    init {
//        if (sharedPreferences.getApiKeyToken().isNotEmpty()){
//            getMessageCount()
//
//        }

    }

//    fun getMessageCount() {
//        viewModelScope.launch {
//            val result: Result<GetMessageCountResponse> = mailUserCase.getMessageCount()
//            _uiState.value = when (result) {
//                is Result.Error -> {
//                    when (result.errorType) {
//                        ErrorType.DataError -> UiState.Error(
//                            MyApplication.appContext.getString(
//                                R.string.data_error
//                            )
//                        )
//
//                        ErrorType.NetworkError -> UiState.Error(
//                            MyApplication.appContext.getString(
//                                R.string.network_error
//                            )
//                        )
//
//                        ErrorType.ServerError -> UiState.Error(
//                            MyApplication.appContext.getString(
//                                R.string.ser_error
//                            )
//                        )
//
//                        ErrorType.UnknownError -> UiState.Error(
//                            MyApplication.appContext.getString(
//                                R.string.unknown
//                            )
//                        )
//
//                        null -> UiState.Error(MyApplication.appContext.getString(R.string.error_happend))
//                    }
//
//                }
//
//                is Result.Loading -> UiState.Loading
//                is Result.Success -> UiState.Success(result.data!!)
//            }
//        }
//    }


    fun navToIdle() {
        _uiState.value = UiState.Idle

    }

    sealed class UiState {
        object Loading : UiState()
        object Idle : UiState()


        class Error(val errorMsg: String) : UiState()
//        class Success(val data: GetMessageCountResponse) : UiState()
//        class SuccessChangePasswordStatus(val data: ChangePasswordAfterLoginResponse) : UiState()

    }
}