package com.asmanmirza.schoolpen.UI.Student.Home.Models

import com.google.gson.annotations.SerializedName


data class ModelUserPeriodById(
    val data: DataPeriod,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataPeriod(
    val authenticated: Boolean,
    @SerializedName("period")
    val periodObj:PeriodObject,
    val status: Int
)


data class PeriodObject(
    val assignedSubject: AssignedSubjectPeriod,
    val assignedTeacher: AssignedTeacherPeriod,
    val assignedTopic: AssignedTopic,
    val beginDate: String,
    val beginTime: String,
    val classes: Classes,
    val conductedByTeacher: ConductedByTeacher,
    val conductedSubject: ConductedSubject,
    val conductedTopic: ConductedTopic,
    val createDateTime: String,
    val endDate: String,
    val endTime: String,
    val id: Int,
    val periodName: String,
    val title: Any,
    val updateDateTime: String
)

data class AssignedSubjectPeriod(
    val classes: List<Any>,
    val createDateTime: Any,
    val id: Int,
    val subjectName: String,
    val updateDateTime: Any
)

data class AssignedTeacherPeriod(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: School,
    val subjects: List<Any>
)

data class AssignedTopic(
    val createDateTime: Any,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: Subject,
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

data class ConductedByTeacher(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: School,
    val subjects: List<Any>
)

data class ConductedSubject(
    val classes: List<Any>,
    val createDateTime: Any,
    val id: Int,
    val subjectName: String,
    val updateDateTime: Any
)


data class ConductedTopic(
    val createDateTime: Any,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: Subject,
    val updateDateTime: String
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

data class Subject(
    val classes: List<Any>,
    val createDateTime: Any,
    val id: Int,
    val subjectName: String,
    val updateDateTime: Any
)