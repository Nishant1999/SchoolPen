package com.asmanmirza.schoolpen.data.api

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 21:12
 */

object NetworkConstants {
    object Api {
        private const val API = "api/v1"
        const val BASE_URL = "http://15.207.234.31:8181/"

        object EndPoints {
            object Auth {
                const val SIGNIN = "$API/authenticate"
                const val SIGNUP = "$API/signUp"
                const val GENERATE_OTP = "$API/generateOtp"
                const val VERIFY_OTP = "$API/verifyOtp"
                const val GET_USER_DETAILS = "$API/user/{userId}"
                const val GET_TODAY_LIVE_CLASS="$API/todayLiveClass"
                //Changes for School ID
                const val GET_NOTICE_DETAILS="$API/viewNoticeBySchoolId/1"

                const val GET_HOMEWORK_DETAILS="$API/getAllHomeWorkBySchoolsClass"

                const val GET_COURSE_DETAILS="$API/readAllCourse"

                const val GET_COURSE_DETAILS_BY_ID="$API/readCourseById/{id}"

                const val GET_COURSE_REVIEW_BY_COURSE_ID="$API/readCourseReviewByCourseId/{id}"

                //changes for Class Id
                const val GET_PERIOD_CLASS_ID="$API/getPeriodByClassId/1"

                const val GET_PERIOD_BY_ID="$API/getPeriod/{id}"

            }
        }
    }
}