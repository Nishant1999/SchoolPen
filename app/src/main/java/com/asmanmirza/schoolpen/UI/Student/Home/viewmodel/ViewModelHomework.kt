package com.asmanmirza.schoolpen.UI.Student.Home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.HomeWork
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.repository.HomeworkRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelHomework @Inject constructor(private val repo: HomeworkRepository) : ViewModel() {

    private val _homework: MutableLiveData<List<HomeWork>> = MutableLiveData()
    val homeworkData: LiveData<List<HomeWork>> = _homework

    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getHomework(schoolId:Int,classId:Int,token: String) {
        viewModelScope.launch {
            repo.getHomework(schoolId,classId,token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res = data.homeWork
                        Log.d("+++++ResultData", res.toString())
                        _homework.postValue(res)
                    }

                }
            }
        }
    }
}