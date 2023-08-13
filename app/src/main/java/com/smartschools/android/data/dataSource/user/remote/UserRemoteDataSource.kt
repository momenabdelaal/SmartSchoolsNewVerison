package com.smartschools.android.data.dataSource.user.remote

import com.google.gson.JsonObject
import com.smartschools.android.data.model.auth.LoginResponse
import com.smartschools.android.data.model.dashboard.DashboardResponse
import com.smartschools.android.domain.network.Result

interface UserRemoteDataSource {

//
    suspend fun userLogin(json: JsonObject): Result<LoginResponse>
    suspend fun getDashboardStudent(): Result<DashboardResponse>
    suspend fun getDashboardTeacher(): Result<DashboardResponse>


//    suspend fun userCompleteSignIn(userId: String, otpCode: String, token: String): Result<CompleteLoginResponse>
//
//    suspend fun userForgetPassword(phone:String): Result<ForgetPasswordResponse>
//    suspend fun userForgetPasswordOtp(code:String,C_Code:String): Result<ForgetPasswordConfirmResponse>
//    suspend fun userForgetPasswordConfirm(token:String,phone:String,smsCode:Int,newPass:String): Result<ForgetPasswordConfirmResponse>
//    suspend fun userChangePassword(oldPass:String,newPass:String): Result<ChangePasswordResponse>
//    suspend fun userGetInfo(): Result<UserGetInfoResponse>
//    suspend fun updateImageProfile(part: MultipartBody.Part): Result<UpdateImageResponse>
//    suspend fun userChangePasswordAfterLogin(newPassword:String,confirmPassword:String): Result<ChangePasswordResponse>
//    suspend fun getPasswordChangedStatus(userId:Int): Result<ChangePasswordAfterLoginResponse>


}