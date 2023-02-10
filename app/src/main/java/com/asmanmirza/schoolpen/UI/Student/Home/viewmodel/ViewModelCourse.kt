package com.asmanmirza.schoolpen.UI.Student.Home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTOId
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelCourse @Inject constructor(private val repo: CourseRepo) : ViewModel() {

    private val _courseData: MutableLiveData<List<CourseDTO>> = MutableLiveData()
    val courseData: LiveData<List<CourseDTO>> = _courseData

    private val _courseDataId: MutableLiveData<CourseDTOId> = MutableLiveData()
    val courseDataId: LiveData<CourseDTOId> = _courseDataId

    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getAllCourse(token: String) {
        viewModelScope.launch {
            repo.getAllCourse(token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res = data.courseDTOs
                      // Log.d("+++++ResultData", res.toString())
                        _courseData.postValue(res)
                    }

                }
            }
        }
    }

    fun getCourseId(courseId:Int,token: String) {
        viewModelScope.launch {
            repo.getCourseId(courseId,token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res = data.courseDTO
                        Log.d("+++++ResultDataID", res.toString())
                        _courseDataId.postValue(res)
                    }

                }
            }
        }
    }


}