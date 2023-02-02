package com.asmanmirza.schoolpen.UI.Student.models



data class ModelUserDetails(
    val authenticated: Any,
    val data: DataUser,
    val message: String,
    val stamp: String,
    val status: Int,
    val tokenData: String,
    val userId: Int
)

data class DataUser(
    val email: String,
    val mobileNumber: String,
    val roles: List<RoleData>,
    val userName: String
)


data class RoleData(
    val description: String,
    val id: Int,
    val name: String
)