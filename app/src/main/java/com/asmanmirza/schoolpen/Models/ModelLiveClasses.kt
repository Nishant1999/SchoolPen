package com.asmanmirza.schoolpen.Models

import com.google.gson.annotations.SerializedName

data class ModelLiveClasses(
    val message: String,
    val stamp: String,
    val status: Int,
    @SerializedName("data")
    val data: DataLiveClass
)

data class DataLiveClass(
    @SerializedName("TodayliveClassDtos ")
    val todayLiveClassDto : List<TodayliveClassDtos>,
    val status: Int
)

data class TodayliveClassDtos(
    val assignedTeacher: AssignedTeacher? =null,
    val classTime: Any? =null,
    val id: Int? =null,
    val scheduledate: String?=null,
    val subjectDescription: String?=null,
    val teacherNumber: String?=null,
    val title: String?=null,
    val user: User?=null,
    val videoURL: String?=null
)


data class AssignedTeacher(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: LiveClassSchool,
    val subjects: List<Any>
)

data class User(
    val address: Any,
    val createDateTime: String,
    val dob: Any,
    val email: String,
    val id: Int,
    val imgUrl: Any,
    val mobileNumber: Any,
    val name: String,
    val numberVerified: Boolean,
    val password: String,
    val refCode: Any,
    val roles: List<Role>,
    val updateDateTime: String,
    val userName: String
)


data class Role(
    val description: String,
    val id: Int,
    val name: String
)

data class LiveClass(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class LiveClassSchool(
    val adderssLine2: Any,
    val addressLine1: String,
    val city: String,
    val contactNumber1: String,
    val contactNumber2: Any,
    val country: Any,
    val id: Int,
    val imgUrl: Any,
    val logo: Any,
    val msg: Any,
    val pin: String,
    val principleName: Any,
    val schoolAboutUs: String,
    val schoolDescription: String,
    val schoolName: String,
    val schoolVision: String,
    val state: String,
    val status: Any,
    val userId: Any
)