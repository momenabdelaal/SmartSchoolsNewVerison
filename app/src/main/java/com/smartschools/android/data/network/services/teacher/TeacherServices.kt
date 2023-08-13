package com.smartschools.android.data.network.services.teacher

import com.google.gson.JsonObject
import com.smartschools.android.data.model.student.assignment.AssignmentStudentResponse
import com.smartschools.android.data.model.student.assignment.subjects.SubjectResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface TeacherServices {

//    http://13.51.219.121/api/teacher/
    @Headers("teacher: application/json")
    @GET("teacher/assignments/all?subject_id=2")
    suspend fun getAssignments(
    ): Response<AssignmentStudentResponse>

    @Headers("Accept: application/json")
    @GET("teacher/teacher-subjects")
    suspend fun getAllSubjects(
    ): Response<SubjectResponse>


    @Multipart
    @Headers("Accept: application/json")
    @POST("teacher/assignments/store")
    suspend fun storeAssignment(
        @Body json: JsonObject,
        @Part files: Array<MultipartBody.Part?>? = null
    ): Response<SubjectResponse>

    @Multipart
    @Headers("Accept: application/json")
    @POST("teacher/assignments/update/{id}")
    suspend fun updateAssignment(
        @Query("assignment_id") assignment_id : Int ,
        @Body json: JsonObject,
        @Part files: Array<MultipartBody.Part?>? = null
    ): Response<SubjectResponse>


    @Headers("Accept: application/json")
    @POST("teacher/assignments/delete/{id}")
    suspend fun deleteAssignment(
        @Query("assignment_id") assignment_id : Int ,
    ): Response<SubjectResponse>
}