package com.smartschools.android.data.model.auth

data class ResultModel(
    var id: Int,
    var mobile: String,
    var must_change_password: Int,
    var must_complete_profile: Int,
    var name: String,
    var photo: String?,
    var school: SchoolModel?,
    var token: String,
    var type: String
)