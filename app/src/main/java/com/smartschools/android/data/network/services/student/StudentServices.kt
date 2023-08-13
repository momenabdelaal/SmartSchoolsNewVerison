package com.smartschools.android.data.network.services.student

import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentServices {

//    http://13.51.219.121/api/student/
    @Headers("Accept: application/json")
    @GET("student/assignments/all")
    suspend fun getAssignments(
    @Query("subject_id") subject_id : Int ,
    ): Response<AssignmentStudentResponse>

    @Headers("Accept: application/json")
    @GET("student/subjects/subjects")
    suspend fun getAllSubjects(
    ): Response<SubjectResponse>

    @Headers("Accept: application/json")
    @GET("student/assignments/show/{id}")
    suspend fun showAssignment(
        @Path("id") v: Int
    ): Response<SubjectResponse>

    @Multipart
    @Headers("Accept: application/json")
    @POST("student/assignments/reply")
    suspend fun replyAssignment(
        @Query("assignment_id") assignment_id : Int ,
        @Query("content")content: String,
        @Part files: Array<MultipartBody.Part?>? = null
    ): Response<SubjectResponse>



}