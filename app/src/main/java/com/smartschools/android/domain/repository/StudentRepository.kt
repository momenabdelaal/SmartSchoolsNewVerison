package com.smartschools.android.domain.repository

import com.smartschools.android.domain.network.Result
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse

interface StudentRepository {


    suspend fun getAssignments(id: Int): Result<AssignmentStudentResponse>
    suspend fun getAllSubjects(): Result<SubjectResponse>


}