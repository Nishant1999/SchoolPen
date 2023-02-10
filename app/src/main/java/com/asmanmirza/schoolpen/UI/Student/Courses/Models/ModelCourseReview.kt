package com.asmanmirza.schoolpen.UI.Student.Courses.Models

import com.google.gson.annotations.SerializedName


data class ModelCourseReview(
    val data: DataReview,
    val message: String,
    val stamp: String,
    val status: Int
)

data class DataReview(
    @SerializedName("courseReviewDTOs  ")
    val courseReviewDTOs  : List<CourseReviewDTO>,
    val status: Int
)

data class CourseReviewDTO(
    val course: Course,
    val feedBackStar: Int,
    val id: Int,
    val review: String,
    val student: Student
)

data class Course(
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
    val school: School,
    val teachers: List<TeacherCourseReview>,
    val topics: List<Topic>,
    val whyApply: String
)

data class SchoolCourseReview(
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
    val classes: ClassesCourseReview,
    val createDateTime: String,
    val date_of_birth: Any,
    val id: Int,
    val name: String,
    val parent: Any,
    val school: SchoolCourseReview,
    val updateDateTime: String,
    val user: Any
)

data class SubjectCourseReview(
    val classes: List<Any>,
    val createDateTime: Any,
    val id: Int,
    val subjectName: String,
    val updateDateTime: Any
)


data class TeacherCourseReview(
    val classes: List<Any>,
    val date_of_birth: Any,
    val email: Any,
    val id: Int,
    val imgURL: Any,
    val location: Any,
    val mobile: Any,
    val name: String,
    val school: SchoolCourseReview,
    val subjects: List<Any>
)

data class TopicCourseReview(
    val createDateTime: String,
    val description: Any,
    val id: Int,
    val name: String,
    val numberOfHours: Int,
    val subject: SubjectCourseReview,
    val updateDateTime: String
)

data class ClassesCourseReview(
    val className: String,
    val createDateTime: String,
    val id: Int,
    val schoolId: Int,
    val updateDateTime: String,
    val userId: Int
)