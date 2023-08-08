package com.smartschools.android.data.model.auth

import com.smartschools.android.data.model.auth.login.auth.SchoolModel

data class ResultModel(
    var id: Int,
    var mobile: String,
    var must_change_password: Int,
    var must_complete_profile: Int,
    var name: String,
    var photo: Any,
    var schoolModel: SchoolModel,
    var token: String,
    var type: String
)