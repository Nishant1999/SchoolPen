package com.asmanmirza.schoolpen.UI.Student.Home.Models




data class ModelClassUserId(
    val data: List<DataUserId>,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataUserId(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

