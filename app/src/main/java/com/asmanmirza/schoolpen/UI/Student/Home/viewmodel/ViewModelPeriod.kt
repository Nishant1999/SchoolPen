package com.asmanmirza.schoolpen.UI.Student.Home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTOId

import com.asmanmirza.schoolpen.UI.Student.Home.Models.PeriodObject
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper

import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.PeriodRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelPeriod @Inject constructor(private val repo: PeriodRepo) : ViewModel() {



    private val _periodDataId: MutableLiveData<PeriodObject> = MutableLiveData()
    val periodDataId: LiveData<PeriodObject> = _periodDataId

    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getPeriodById(periodId:Int,token: String) {
        viewModelScope.launch {
            repo.getPeriodById(periodId,token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res = data.periodObj
                        // Log.d("+++++ResultData", res.toString())
                        _periodDataId.postValue(res)
                    }
                }
            }
        }
    }
}