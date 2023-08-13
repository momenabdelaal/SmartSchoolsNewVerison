package com.smartschools.android.ui.users.student.assignments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse
import com.smartschools.android.domain.network.Result
import com.smartschools.android.domain.usecase.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAssignmentsViewModel @Inject constructor(private val useCase: StudentUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        getAllSubject()
    }

    fun getAssignmentsStudent(id: Int) {


        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = useCase.getAssignments(id)
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

    fun getAllSubject() {


        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = useCase.getAllSubjects()
            _uiState.value = when (result) {
                is Result.Loading -> UiState.Loading
                is Result.Error -> {
                    UiState.Error(result.errorType)
                }

                is Result.Success -> {
                    UiState.SuccessSubjects(result.data!!)

                }
            }
        }
    }


    //used to reset state after navigation
    fun navigateToFragment() {
        _uiState.value = UiState.Idle
    }

    sealed class UiState {
        object Loading : UiState()
        object Idle : UiState()
        class Error(val errorMsg: String) : UiState()
        class Success(val data: AssignmentStudentResponse) : UiState()
        class SuccessSubjects(val data: SubjectResponse) : UiState()
    }

}