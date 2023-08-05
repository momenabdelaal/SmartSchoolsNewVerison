package com.smartschools.android.data.model.dashboard

data class DashboardResponse(
    val message: String,
    val result: DashboardResult,
    val status: Boolean
)