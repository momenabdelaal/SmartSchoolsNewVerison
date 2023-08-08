package com.smartschools.android.data.model.dashboard

data class DashboardResponse(
    val message: String,
    val data: DashboardResult,
    val status: Boolean
)