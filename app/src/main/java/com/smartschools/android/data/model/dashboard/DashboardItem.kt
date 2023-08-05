package com.smartschools.android.data.model.dashboard

data class DashboardItem(
    val count: Int,
    val icon: String,
    val name: String,
    val navigation: Boolean,
    val text: String
)