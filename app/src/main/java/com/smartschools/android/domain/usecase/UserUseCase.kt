package com.smartschools.android.domain.usecase

import com.smartschools.android.domain.repository.UserRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepo: UserRepository) {


//    fun saveApiKeyToken(apiKey: String) = userRepo.saveApiKeyToken(apiKey)
//    fun saveRememberMe(rememberMe: String) = userRepo.saveRememberMe(rememberMe)

//    suspend fun userLogin(userName: String, pass: String) =
//        userRepo.userLogin(userName, pass)
//
//
//    suspend fun userCompleteSignIn(userId: String, otpCode: String, token: String ) =
//        userRepo.userCompleteSignIn(userId, otpCode,token)
//
//    suspend fun userForgetPassword(phone: String) =
//        userRepo.userForgetPassword(phone)
//
//    suspend fun userForgetPasswordOtp(code:String,C_Code:String) = userRepo.userForgetPasswordOtp(code, C_Code)
//
//    suspend fun userForgetPasswordConfirm(token:String,phone:String,smsCode:Int,newPass:String) =
//        userRepo.userForgetPasswordConfirm(token,phone,smsCode,newPass)
//    suspend fun userChangePassword(oldPass:String,newPass:String) =
//        userRepo.userChangePassword(oldPass, newPass)
//
//
//    suspend fun userGetInfo() = userRepo.userGetInfo()
//
//    suspend fun updateImageProfile(part: MultipartBody.Part) = userRepo.updateImageProfile(part)
//
//    suspend fun userChangePasswordAfterLogin(newPassword:String,confirmPassword:String) =
//        userRepo.userChangePasswordAfterLogin(newPassword, confirmPassword)
//
//    suspend fun getPasswordChangedStatus(userId:Int) =
//        userRepo.getPasswordChangedStatus(userId)



}