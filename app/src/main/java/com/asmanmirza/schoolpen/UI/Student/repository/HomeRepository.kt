package com.asmanmirza.schoolpen.UI.Student.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.DataLiveClass
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.UI.Student.di.NetworkHelper
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserDetails
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserPeriod
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val application: Application) {

    val myApi: MyApi = ApiClient.getClient()?.create(MyApi::class.java)!!

    fun getTodayLiveData(token: String
    ): kotlinx.coroutines.flow.Flow<ResultWrapper<ModelLiveClasses>> {
        return flow {


            val response = myApi.getLiveClass(token)
            if (response.isSuccessful) {
                response.body()?.let {

                    Log.d("+++++Response data",response.body().toString())
                    emit(ResultWrapper.Success(it))
                }
            } else {
                val error = NetworkHelper.ErrorResponse()
                error.message = "Something went wrong"
                emit(ResultWrapper.GenericError(error = error))
            }
        }
    }

    fun getPeriodClassId(token: String
    ): kotlinx.coroutines.flow.Flow<ResultWrapper<ModelUserPeriod>> {
        return flow {


            val response = myApi.getPeriodByClassId(token)
            if (response.isSuccessful) {
                response.body()?.let {

                    Log.d("+++++Response data",response.body().toString())
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