package com.asmanmirza.schoolpen.UI.Student.Courses.Models

import com.google.gson.annotations.SerializedName


data class ModelCourseId(
    val data: DataCourseId,
    val message: String,
    val stamp: String,
    val status: Int
)
data class DataCourseId(
    @SerializedName("courseDTO ")
    val courseDTO : CourseDTOId,
    val status: Int
)


data class CourseDTOId(
    val aboutCourse: String,
    val certificateDetails: String,
    val certificateName: Any,
    val courseCategory: String,
    val courseDurationInWeeks: String,
    val courseFee: Int,
    val courseName: String,
    val courseTitle: String,
    val dificultyLevel: String,
    val id: Int,
    val requirement: String,
    val school: SchoolCourseId,
    val teachers: List<TeacherCourseId>,
    val topics: List<TopicCourseId>,
    val whyApply: String
)

data class ClassesCourseId(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)

data class SchoolCourseId(
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

data class SubjectCourseId(
    //val classes: List<Any>,
    val createDateTime: Any,
    val id: Int,
    val subjectName: String,
    val updateDateTime: Any
)

data class TeacherCourseId(
    val classId: List<Any>,
    val classes: List<Any>,
    val id: Int,
    val name: String,
    val school: School
)

data class TopicCourseId(
    val createDateTime: String,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: SubjectCourseId,
    val updateDateTime: String
)