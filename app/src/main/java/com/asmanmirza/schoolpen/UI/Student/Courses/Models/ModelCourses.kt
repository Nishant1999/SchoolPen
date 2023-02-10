package com.asmanmirza.schoolpen.UI.Student.Courses.Models

import com.google.gson.annotations.SerializedName

//class ModelCourses(var id:String, var title:String, var skills:String, var rating:String, var price:String, var bannerUrl:String)

data class ModelCourses(
    val data: DataCourses,
    val message: String,
    val stamp: String,
    val status: Int
)




data class DataCourses(
    @SerializedName("courseDTOs ")
    val courseDTOs : List<CourseDTO>,
    val status: Int
)

data class CourseDTO(
    val aboutCourse: String,
    val certificateDetails: String,
    val certificateName: Any,
    val courseCategory: String,
    val courseDurationInWeeks: String,
    val courseFee: Int,
    val courseName: String,
    val courseTitle: String,
    //val skills: String,
    val dificultyLevel: String,
    val id: Int,
    val requirement: String,
    val school: School,
    val teachers: List<Teacher>,
    val topics: List<Topic>,
    val whyApply: String
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

data class Classes(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)
data class Teacher(
    val classes: List<Classes>,
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

data class Topic(
    val createDateTime: String,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: Subject,
    val updateDateTime: String
)