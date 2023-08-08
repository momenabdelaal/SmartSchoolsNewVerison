package com.smartschools.android.data.network.services.student

import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface StudentServices {

//    http://13.51.219.121/api/student/
    @Headers("Accept: application/json")
    @GET("student/assignments/all?subject_id=2")
    suspend fun getAssignments(
    ): Response<AssignmentStudentResponse>



}