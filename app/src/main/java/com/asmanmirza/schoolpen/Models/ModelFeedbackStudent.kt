package com.asmanmirza.schoolpen.Models

import com.google.gson.annotations.SerializedName


data class ModelFeedbackStudent(
    val data: DataFeedBackStudent,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataFeedBackStudent(
    @SerializedName("feedBackForStudentByTeacherDTOs ")
    val feedBackForStudentByTeacherDTOs : List<FeedBackForStudentByTeacherDTO>,
    val status: Int
)

data class FeedBackForStudentByTeacherDTO(
    val feedBackDetails: String,
    val id: Int,
    val student: Student,
    val teacher: Teacher
)

data class ClassesFeedBackStudent(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class SchoolFeedBackStudent(
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
    val classes: ClassesFeedBackStudent,
    val createDateTime: String,
    val date_of_birth: Any,
    val id: Int,
    val name: String,
    val parent: Any,
    val school: SchoolFeedBackStudent,
    val updateDateTime: String,
    val user: Any
)

data class Teacher(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: SchoolFeedBackStudent,
    val subjects: List<Any>
)