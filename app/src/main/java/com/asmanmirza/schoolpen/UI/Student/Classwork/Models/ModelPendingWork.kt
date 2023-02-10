package com.asmanmirza.schoolpen.UI.Student.Classwork.Models

import com.google.gson.annotations.SerializedName

//class ModelPendingWork(var id:String, var title:String, var dueDate:String, var chTitle:String, var chDescription:String)
data class ModelPendingWork(
    val data: DataHomework,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataHomework(
    @SerializedName("homeWorks  ")
    val homeWork  : List<HomeWork>,
    val status: Int
)

data class HomeWork(
    val classes: HomeWorkClasses,
    val createDateTime: String,
    val description: String,
    val id: Int,
    val imgUrl: String,
    val questions: List<Question>,
    val subject: Subject,
    val teacher: Teacher,
    val title: String,
    val topic: Topic,
    val updateDateTime: String
)

data class HomeWorkClasses(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)
data class Question(
    val answer: Any,
    val category: String,
    val createDateTime: String,
    val difficulty: Int,
    val id: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val question: String,
    val test: Test,
    val updateDateTime: String
)
data class HomeWorkSchool(
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

data class Teacher(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: HomeWorkSchool,
    val subjects: List<Any>
)

data class Test(
    val classes: HomeWorkClasses,
    val createDateTime: Any,
    val description: Any,
    val id: Int,
    val imgURL: Any,
    val marks: Any,
    val minTime: Any,
    val testTime: Any,
    val title: Any,
    val updateDateTime: Any
)

data class Topic(
    val createDateTime: Any,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: Subject,
    val updateDateTime: String
)