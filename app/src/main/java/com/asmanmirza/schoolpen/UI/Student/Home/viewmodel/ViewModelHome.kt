package com.asmanmirza.schoolpen.UI.Student.Home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.models.DataUser
import com.asmanmirza.schoolpen.UI.Student.models.Period
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.repository.UserDetailsRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ViewModelHome @Inject constructor(private val repo: HomeRepository) : ViewModel() {

    private val _todayLiveData: MutableLiveData<List<TodayliveClassDtos>> = MutableLiveData()
    val todayData: LiveData<List<TodayliveClassDtos>> = _todayLiveData

    private val _todayPeriodData: MutableLiveData<List<Period>> = MutableLiveData()
    val todayPeriodData: LiveData<List<Period>> = _todayPeriodData

    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getTodayLiveData(token: String) {
        viewModelScope.launch {
            repo.getTodayLiveData(token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res=data.todayLiveClassDto
                        Log.d("+++++ResultData",res.toString())
                        _todayLiveData.postValue(res)
                    }

                }
            }
        }
    }

    fun getPeriodClassId(token: String) {
        viewModelScope.launch {
            repo.getPeriodClassId(token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res=data.period
                        Log.d("+++++ResultData",res.toString())
                        _todayPeriodData.postValue(res)
                    }

                }
            }
        }
    }
}