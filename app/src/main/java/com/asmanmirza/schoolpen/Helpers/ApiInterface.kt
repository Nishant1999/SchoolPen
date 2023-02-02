package com.asmanmirza.schoolpen.Helpers

import com.asmanmirza.schoolpen.data.api.NetworkConstants
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @POST(NetworkConstants.Api.EndPoints.Auth.SIGNIN)
    fun login(@Body body: JsonObject): Call<Any>

    @POST(NetworkConstants.Api.EndPoints.Auth.SIGNUP)
    fun register(@Body body: JsonObject): Call<Any>

    @Headers("Content-Type: application/json")
    @POST(NetworkConstants.Api.EndPoints.Auth.GENERATE_OTP)
    fun sendOTP(@Body body: JsonObject, @Header("Authorization") token:String): Call<Any>

    @Headers("Content-Type: application/json")
    @POST(NetworkConstants.Api.EndPoints.Auth.VERIFY_OTP)
    fun verifyOTP(@Body body: JsonObject,  @Header("Authorization") token:String): Call<Any>

}