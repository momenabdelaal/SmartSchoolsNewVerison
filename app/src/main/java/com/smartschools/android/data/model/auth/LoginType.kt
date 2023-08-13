package com.smartschools.android.data.model.auth

data class LoginType(
    val name: String,
    val contentName: String



) {
    override fun toString(): String {
        return name
    }
}