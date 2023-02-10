package com.asmanmirza.schoolpen.UI.Student.repository

import android.app.Application
import android.util.Log
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelUserPeriodById
import com.asmanmirza.schoolpen.UI.Student.di.NetworkHelper
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserPeriod
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeriodRepo @Inject constructor(private val application: Application) {

    val myApi: MyApi = ApiClient.getClient()?.create(MyApi::class.java)!!

    fun getPeriodById(
        periodId:Int,
        token: String
    ): kotlinx.coroutines.flow.Flow<ResultWrapper<ModelUserPeriodById>> {
        return flow {


            val response = myApi.getPeriodId(periodId,token)
            if (response.isSuccessful) {
                response.body()?.let {

                    Log.d("+++++Response data", response.body().toString())
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
