package com.smartschools.android.data.dataSource.user.remote

import com.smartschools.android.data.network.NetworkServices
import com.smartschools.android.data.network.RequestApiCall
import com.google.gson.JsonObject
import com.smartschools.android.data.model.auth.login.auth.LoginResponse
import com.smartschools.android.data.model.dashboard.DashboardResponse
import com.smartschools.android.domain.network.Result
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val networkServices: NetworkServices,
    private val requestApiCall: RequestApiCall,
) : UserRemoteDataSource {


    override suspend fun userLogin(
        json: JsonObject
    ): Result<LoginResponse> {
        val res = requestApiCall.requestApiCall {
            networkServices.login(
                json = json
            )
        }

        return if (res is Result.Success && res.data != null) {
            Result.Success(res.data)
        } else {
            Result.Error(res.errorType)
        }
    }

    override suspend fun getDashboard(): Result<DashboardResponse> {
        val res = requestApiCall.requestApiCall {
            networkServices.getDashboard()
        }

        return if (res is Result.Success && res.data != null) {
            Result.Success(res.data)
        } else {
            Result.Error(res.errorType)
        }
    }
//
//    override suspend fun userCompleteSignIn(
//        userId: String,
//        otpCode: String,
//        token: String
//    ): Result<CompleteLoginResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userCompleteSignIn(
//                userId = userId,
//                code = otpCode,
//                c_token = token
//            )
//        }
//
//        return if (res is Result.Success && res.data != null) {
//            Result.Success(res.data)
//        } else {
//            Result.Error(res.errorType)
//        }
//    }
//
//    override suspend fun userForgetPassword(
//        phone: String
//    ): Result<ForgetPasswordResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userForgetPassword(
//                phone = phone
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//    }
//
//    override suspend fun userForgetPasswordOtp(
//        code: String,
//        C_Code: String
//    ): Result<ForgetPasswordConfirmResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userForgetPasswordOtp(
//                code = code,
//                C_Code = C_Code,
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }
//    override suspend fun userForgetPasswordConfirm(
//        token: String,
//        phone: String,
//        smsCode: Int,
//        newPass: String
//    ): Result<ForgetPasswordConfirmResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userForgetPasswordConfirm(
//                token = token,
//                phone = phone,
//                smsCode = smsCode,
//                newPass = newPass
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }
//
//    override suspend fun userChangePassword(
//        oldPass: String,
//        newPass: String
//    ): Result<ChangePasswordResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userChangePassword(
//                oldPass = oldPass,
//                newPass = newPass
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }
//
//    override suspend fun userGetInfo(
//    ): Result<UserGetInfoResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userGetInfo(
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }
//
//    override suspend fun updateImageProfile(part: MultipartBody.Part): Result<UpdateImageResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.updateImageProfile(
//                Constants.VERSION_APIS,
//                SharedPreferencesImpl(MyApplication.appContext).getLanguage(),part)
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//    }
//    override suspend fun userChangePasswordAfterLogin(
//        newPassword:String,confirmPassword:String
//    ): Result<ChangePasswordResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.userChangePasswordAfterLogin(
//                newPass = newPassword,
//                confirmPass = confirmPassword
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }
//    override suspend fun getPasswordChangedStatus(
//        userId:Int
//    ): Result<ChangePasswordAfterLoginResponse> {
//        val res = requestApiCall.requestApiCall {
//            networkServices.getPasswordChangedStatus(
//                userId = userId,
//
//            )
//        }
//        return if (res is Result.Success && res.data != null)
//            Result.Success(res.data)
//        else
//            Result.Error(res.errorType)
//
//    }


}