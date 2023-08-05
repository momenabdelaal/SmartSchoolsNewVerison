package com.smartschools.android.data.model.dashboard

data class DashboardResult(
    val bus: Any,
    val `class`: String,
    val dashboard_items: List<DashboardItem>,
    val grade: String,
    val id: Int,
    val name: String,
    val stage: String
)