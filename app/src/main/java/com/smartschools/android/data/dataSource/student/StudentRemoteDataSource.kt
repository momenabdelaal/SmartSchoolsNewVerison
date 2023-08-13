package com.smartschools.android.data.dataSource.student

import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse
import com.smartschools.android.domain.network.Result

interface StudentRemoteDataSource {

    suspend fun getAssignments(id: Int): Result<AssignmentStudentResponse>
    suspend fun getAllSubjects(): Result<SubjectResponse>


}