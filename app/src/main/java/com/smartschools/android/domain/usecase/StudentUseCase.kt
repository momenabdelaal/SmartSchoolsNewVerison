package com.smartschools.android.domain.usecase

import com.smartschools.android.domain.repository.StudentRepository
import javax.inject.Inject

class StudentUseCase @Inject constructor(private val repo: StudentRepository) {





    suspend fun getAssignments(id: Int) = repo.getAssignments(id)
    suspend fun getAllSubjects() = repo.getAllSubjects()


}