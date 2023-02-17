package com.asmanmirza.schoolpen.UI.Student.retrofit

import com.asmanmirza.schoolpen.Models.ModelFeedbackStudent
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.Models.ModelNotice
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelPendingWork
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTest
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourseId
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourseReview
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourses
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelClassUserId
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelStudentNotification
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelTeacherNote
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelUserPeriodById
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserDetails
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserPeriod
import com.asmanmirza.schoolpen.data.api.NetworkConstants
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


/**
 * Network API Interface
 */
interface MyApi {


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_USER_DETAILS)
    suspend fun getUser(
        @Path("userId") userId: Int,
        @Header("Authorization") token: String
    ): Response<ModelUserDetails>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_TODAY_LIVE_CLASS)
     suspend fun getLiveClass(
        @Header("Authorization") token: String
    ): Response<ModelLiveClasses>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_NOTICE_DETAILS)
     fun getNotice(
        @Path("id") schoolId: Int,
        @Header("Authorization") token: String
    ):Call<ModelNotice>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_HOMEWORK_DETAILS)
    suspend fun getPendingHomework(
        @Query("schoolId") schoolId: Int,
        @Query("classId") classId:Int,
        @Header("Authorization") token: String
    ): Response<ModelPendingWork>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_COURSE_DETAILS)
    suspend fun getCourses(
        @Header("Authorization") token: String
    ): Response<ModelCourses>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_COURSE_DETAILS_BY_ID)
    suspend fun getCourseById(
        @Path("id") courseId: Int,
        @Header("Authorization") token: String
    ): Response<ModelCourseId>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_COURSE_REVIEW_BY_COURSE_ID)
    suspend fun getCourseReviewByCourseId(
        @Path("id") reviewId: Int,
        @Header("Authorization") token: String
    ): Response<ModelCourseReview>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_PERIOD_CLASS_ID)
      fun getPeriodByClassId(
        @Path("id") classId: Int,
        @Header("Authorization") token: String
    ): Call<ModelUserPeriod>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_PERIOD_BY_ID)
    suspend fun getPeriodId(
        @Path("id") periodId: Int,
        @Header("Authorization") token: String
    ): Response<ModelUserPeriodById>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_FEEDBACK_BY_STUDENT_ID)
    fun getFeedbackStudentId(
        @Path("id") studentId: Int,
        @Header("Authorization") token: String
    ):Call<ModelFeedbackStudent>

    @GET(NetworkConstants.Api.EndPoints.Auth.GET_NOTIFICATION_DETAILS)
    fun getAllNotification(
        @Header("Authorization") token: String
    ):Call<ModelStudentNotification>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_CLASS_BY_USER_ID)
    fun getClassUserId(
        @Path("userId") userId: Int,
        @Header("Authorization") token: String
    ):Call<ModelClassUserId>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_TEACHER_NOTE)
    fun getAllTeacherNote(
        @Header("Authorization") token: String
    ):Call<ModelTeacherNote>


    @GET(NetworkConstants.Api.EndPoints.Auth.GET_TEST_BY_CLASS_ID)
    fun getClassTestById(
        @Path("id") classId: Int,
        @Header("Authorization") token: String
    ):Call<ModelTest>


}
