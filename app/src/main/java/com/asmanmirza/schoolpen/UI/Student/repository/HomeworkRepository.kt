package com.asmanmirza.schoolpen.UI.Student.repository

import android.app.Application
import android.util.Log
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelPendingWork
import com.asmanmirza.schoolpen.UI.Student.di.NetworkHelper
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeworkRepository @Inject constructor(private val application: Application) {

    val myApi: MyApi = ApiClient.getClient()?.create(MyApi::class.java)!!

    fun getHomework(
        schoolId:Int,
        classId:Int,
        token: String
    ): kotlinx.coroutines.flow.Flow<ResultWrapper<ModelPendingWork>> {
        return flow {


            val response = myApi.getPendingHomework(schoolId,classId,token)
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

            /* db = TinyDB(application);
            myApi.getLiveClass("Bearer" + " " + db.getString("token"))
                .enqueue(object : Callback<ModelLiveClasses> {
                    override fun onResponse(
                        call: Call<ModelLiveClasses>,
                        response: Response<ModelLiveClasses>
                    ) {

                        emit(ResultWrapper.Success(response))
                        val resData: DataLiveClass = response.body()!!.data
                        todayliveClassDtos =
                            resData.todayLiveClassDto as ArrayList<TodayliveClassDtos>
                        mutableLiveData.value = todayliveClassDtos
                    }

                    override fun onFailure(call: Call<ModelLiveClasses>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }*/
        }
    }
}