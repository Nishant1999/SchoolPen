package com.asmanmirza.schoolpen.UI.Student.Home.Models

import com.google.gson.annotations.SerializedName


data class ModelTeacherNote(
    val data: DataNote,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataNote(
    val status: Int,
    @SerializedName("teacherNotes ")
    val teacherNotes : List<TeacherNote>
)


data class TeacherNote(
    val createDateTime: String,
    val id: Int,
    val teacher: Teacher,
    val teacherNote: String,
    val updateDateTime: String
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
    val school: SchoolNote,
    val subjects: List<Any>
)

data class SchoolNote(
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