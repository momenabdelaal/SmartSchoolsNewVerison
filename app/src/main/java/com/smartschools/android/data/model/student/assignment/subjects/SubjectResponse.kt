package com.smartschools.android.data.model.student.assignment.subjects

data class SubjectResponse(
    val `data`: List<SubjectData>,
    val message: String,
    val status: Boolean
)