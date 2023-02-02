package com.asmanmirza.schoolpen.UI.Student.retrofit

import com.asmanmirza.schoolpen.UI.Student.models.ModelUserDetails
import com.asmanmirza.schoolpen.data.api.NetworkConstants
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

}
