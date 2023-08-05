package com.smartschools.android.data.network.services

import com.google.gson.JsonObject
import com.smartschools.android.core.network.MyApplication
import com.smartschools.android.data.model.auth.login.auth.LoginResponse
import com.smartschools.android.data.model.dashboard.DashboardResponse
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserServices {



    @POST("auth/login")
    suspend fun login(@Body json: JsonObject): Response<LoginResponse>
//    @Headers("Content-Type: application/json")

//    http://13.51.219.121/api/studentent/
    @GET("student/dashboard/")
    suspend fun getDashboard(
    ): Response<DashboardResponse>
//
//
//    @POST("Dashboard/v{version}/Auth/Complete_SignIn")
//    suspend fun userCompleteSignIn(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("UserID") userId: String,
//        @Query("Code") code: String,
//        //token
//        @Query("C_Code") c_token: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<CompleteLoginResponse>
//
//
//    @POST("Dashboard/v{version}/Auth/ForgetPass")
//    suspend fun userForgetPassword(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("Phone") phone: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<ForgetPasswordResponse>
//
//    @POST("Dashboard/v{version}/Auth/ForgetPass_ConfirmOTP")
//    suspend fun userForgetPasswordOtp(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("code") code: String,
//        @Query("C_Code") C_Code: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<ForgetPasswordConfirmResponse>
//
//    @POST("Dashboard/v{version}/Auth/ForgetPass_Confirm")
//    suspend fun userForgetPasswordConfirm(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("C_Code") token: String,
//        @Query("Phone") phone: String,
//        @Query("code") smsCode: Int,
//        @Query("NewPass") newPass: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage(),
//
//        ): Response<ForgetPasswordConfirmResponse>
//
//    @POST("Dashboard/v{version}/Auth/ChangePassword")
//    suspend fun userChangePassword(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("OldPass") oldPass: String,
//        @Query("NewPass") newPass: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<ChangePasswordResponse>
//
//    @POST("Dashboard/v{version}/Auth/GetMyInfo")
//    suspend fun userGetInfo(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<UserGetInfoResponse>
//
//    @Multipart
//    @POST("Dashboard/v{version}/Auth/UpdateUserImage")
//    suspend fun updateImageProfile(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage(),
//        @Part part: MultipartBody.Part
//    ): Response<UpdateImageResponse>
//
//
//    @POST("Dashboard/v{version}/Auth/CheckPassChanged")
//    suspend fun userChangePasswordAfterLogin(
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("Pass") newPass: String,
//        @Query("confirmPass") confirmPass: String,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage()
//    ): Response<ChangePasswordResponse>
//
//
//    @GET("Dashboard/v{version}/Auth/getPasswordChangedStatus")
//    suspend fun getPasswordChangedStatus(
//
//        @Path("version") v: Int = VERSION_APIS,
//        @Query("culture") culture: String = SharedPreferencesImpl(MyApplication.appContext).getLanguage(),
//        @Query("userId") userId: Int
//    ): Response<ChangePasswordAfterLoginResponse>


}