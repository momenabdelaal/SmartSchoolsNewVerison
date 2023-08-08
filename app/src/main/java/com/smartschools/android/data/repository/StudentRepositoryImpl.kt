package com.smartschools.android.data.repository


import com.smartschools.android.data.dataSource.student.StudentRemoteDataSource
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.domain.network.Result
import com.smartschools.android.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val remoteDataSource: StudentRemoteDataSource
) : StudentRepository {



    override suspend fun getAssignments(): Result<AssignmentStudentResponse>
        = remoteDataSource.getAssignments()




}