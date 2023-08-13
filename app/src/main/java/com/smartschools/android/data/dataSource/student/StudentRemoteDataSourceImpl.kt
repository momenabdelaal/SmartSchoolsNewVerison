package com.smartschools.android.data.dataSource.student

import android.util.Log
import com.smartschools.android.data.network.NetworkServices
import com.smartschools.android.data.network.RequestApiCall
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse
import com.smartschools.android.domain.network.Result
import javax.inject.Inject

class StudentRemoteDataSourceImpl @Inject constructor(
    private val networkServices: NetworkServices,
    private val requestApiCall: RequestApiCall,
) : StudentRemoteDataSource {




    override suspend fun getAssignments(id: Int): Result<AssignmentStudentResponse> {
        val res = requestApiCall.requestApiCall {
            networkServices.getAssignments(id)
        }

        return if (res is Result.Success && res.data != null) {
            Result.Success(res.data)
        } else {
            Log.d("err", "getDashboard: ")
            Result.Error(res.errorType)
        }
    }
    override suspend fun getAllSubjects(): Result<SubjectResponse> {
        val res = requestApiCall.requestApiCall {
            networkServices.getAllSubjects()
        }

        return if (res is Result.Success && res.data != null) {
            Result.Success(res.data)
        } else {
            Log.d("err", "getDashboard: ")
            Result.Error(res.errorType)
        }
    }


}