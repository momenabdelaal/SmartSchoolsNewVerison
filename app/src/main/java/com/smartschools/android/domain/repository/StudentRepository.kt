package com.smartschools.android.domain.repository

import com.smartschools.android.domain.network.Result
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse

interface StudentRepository {


    suspend fun getAssignments(): Result<AssignmentStudentResponse>


}