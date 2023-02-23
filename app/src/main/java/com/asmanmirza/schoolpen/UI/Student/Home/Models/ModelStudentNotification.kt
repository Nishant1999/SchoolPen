package com.asmanmirza.schoolpen.UI.Student.Home.Models


data class ModelStudentNotification(
    val data: DataNotification,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataNotification(
    val notificationDTOs: List<NotificationDTO>,
    val status: Int
)

data class NotificationDTO(
    val classes: List<ClassesNotification>,
    val dateOfNotification: String,
    val id: Int,
    val imgURL: String,
    val managements: List<Management>,
    val message: String,
    val parents: List<Parent>,
    val schools: List<SchoolNotification>,
    val students: List<Student>,
    val subTitle: String,
    val teachers: List<TeacherNotification>,
    val time: String,
    val title: String,
    val type: String,
    val users: List<UserNotification>
)


data class ClassesNotification(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class Management(
    val createDateTime: String,
    val id: Int,
    val school: SchoolNotification,
    val updateDateTime: String,
    val user: List<UserNotification>
)

data class Student(
    val classes: Classes,
    val createDateTime: String,
    val date_of_birth: String,
    val id: Int,
    val name: String,
    val parent: Any,
    val school: SchoolNotification,
    val updateDateTime: String,
    val user: UserNotification
)

data class SchoolNotification(
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


data class Parent(
    val createDateTime: String,
    val email: String,
    val id: Int,
    val mobile: String,
    val name: String,
    val students: List<Student>,
    val updateDateTime: String
)

data class TeacherNotification  (
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: SchoolNotification,
    val subjects: List<Any>
)

data class UserNotification(
    val DOB: Any,
    val address: Any,
    val createDateTime: String,
    val dob: Any,
    val email: String,
    val id: Int,
    val imgUrl: Any,
    val mobileNumber: Long,
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