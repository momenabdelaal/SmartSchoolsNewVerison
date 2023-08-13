package com.smartschools.android.data.model.student.assignment.subjects

data class SubjectData(
    val id: Int,
    val name: String


) {
    override fun toString(): String {
        return name
    }
}