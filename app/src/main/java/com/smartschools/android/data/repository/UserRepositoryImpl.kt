package com.smartschools.android.data.repository

import com.smartschools.android.data.dataSource.user.local.UserLocalDataSource
import com.smartschools.android.data.dataSource.user.remote.UserRemoteDataSource
import com.smartschools.android.domain.repository.UserRepository

import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalSource: UserLocalDataSource,
    private val userRemoteSource: UserRemoteDataSource
) : UserRepository {

//    override suspend fun completeSignIn(number: String, otpCode: String, token: String) =
//        userRemoteSource.completeSignIn(number, otpCode, token)
//
//    override suspend fun login(number: String) = userRemoteSource.login(number)

//    override fun saveApiKeyToken(apikey: String) = userLocalSource.setToken(apikey)
//    override fun saveRememberMe(rememberMe: String) = userLocalSource.setRememberMe(rememberMe)
//    override suspend fun userLogin(
//        userName: String,
//        pass: String
//    ): Result<LoginResponse> =
//        userRemoteSource.userLogin(userName, pass)
//
//    override suspend fun userCompleteSignIn(
//        userId: String, otpCode: String, token: String
//    ): Result<CompleteLoginResponse> =
//        userRemoteSource.userCompleteSignIn(userId, otpCode, token)
//
//    override suspend fun userForgetPassword(
//        phone: String
//    ): Result<ForgetPasswordResponse> =
//        userRemoteSource.userForgetPassword(phone)
//
//    override suspend fun userForgetPasswordConfirm(
//        token: String,
//        phone: String,
//        smsCode: Int,
//        newPass: String
//    ): Result<ForgetPasswordConfirmResponse> =
//        userRemoteSource.userForgetPasswordConfirm(token, phone, smsCode, newPass)
//
//    override suspend fun userForgetPasswordOtp(
//        code: String,
//        C_Code: String
//    ): Result<ForgetPasswordConfirmResponse> =
//        userRemoteSource.userForgetPasswordOtp(code, C_Code)
//
//
//    override suspend fun userChangePassword(
//        oldPass: String,
//        newPass: String
//    ): Result<ChangePasswordResponse> =
//        userRemoteSource.userChangePassword(oldPass, newPass)
//
//    override suspend fun userGetInfo(): Result<UserGetInfoResponse> =
//        userRemoteSource.userGetInfo()
//    override suspend fun updateImageProfile(part: MultipartBody.Part): Result<UpdateImageResponse> =
//        userRemoteSource.updateImageProfile(part)
//
//
//    override suspend fun userChangePasswordAfterLogin(
//        newPassword:String,confirmPassword:String
//    ): Result<ChangePasswordResponse> =
//        userRemoteSource.userChangePasswordAfterLogin(newPassword, confirmPassword)
//
//
//
//    override suspend fun getPasswordChangedStatus(
//     userId:Int
//    ): Result<ChangePasswordAfterLoginResponse> =
//        userRemoteSource.getPasswordChangedStatus(userId)

}