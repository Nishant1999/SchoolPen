package com.asmanmirza.schoolpen.UI.Student.Classwork.Models

import com.google.gson.annotations.SerializedName

//class ModelTest(var id:String, var title:String, var subject:String, var date:String, var marks:String, var duration:String,var imageID:Int, var upcoming:Boolean)

data class ModelTest(
    val data: DataTest,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataTest(
    val status: Int,
    @SerializedName("tests ")
    val tests : List<TestData>
)


data class TestData(
    val beginDate: String,
    val beginTime: String,
    val classes: Classes,
    val createDateTime: String,
    val description: String,
    val endDate: String,
    val endTime: String,
    val id: Int,
    val imgURL: Int,
    val marks: String,
    val minTime: String,
    val student: Student,
    val title: String,
    val updateDateTime: String
)



data class Classes(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class Role(
    val description: String,
    val id: Int,
    val name: String
)

data class School(
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

data class Student(
    val about: Any,
    val classes: Classes,
    val completeAddress: Any,
    val createDateTime: String,
    val date_of_birth: String,
    val dpURL: Any,
    val email: Any,
    val id: Int,
    val mobileName: Any,
    val name: String,
    val parent: Any,
    val school: School,
    val title: Any,
    val updateDateTime: String,
    val user: User
)

data class User(
    val DOB: Any,
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