package com.asmanmirza.schoolpen.domain.model.auth

data class AuthResponse(
    val `data`: Data,
    val message: String,
    val stamp: String,
    val status: String,
    val userId: Int
)