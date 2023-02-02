package com.asmanmirza.schoolpen.UI.Student.repository

import com.asmanmirza.schoolpen.UI.Student.di.NetworkHelper
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserDetails
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Constructor
import java.util.concurrent.Flow
import javax.inject.Inject

class UserDetailsRepo @Inject constructor(private val myApi: MyApi) {

    suspend fun getUserDetails(
        userId: Int,
        token: String
    ): kotlinx.coroutines.flow.Flow<ResultWrapper<ModelUserDetails>> {
        return flow {
            val response = myApi.getUser(userId, token)

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResultWrapper.Success(it))
                }
            } else {
                val error = NetworkHelper.ErrorResponse()
                error.message = "Something went wrong"
                emit(ResultWrapper.GenericError(error = error))
            }
        }
    }
}