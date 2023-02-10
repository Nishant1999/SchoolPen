package com.asmanmirza.schoolpen.UI.Student.Home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTOId
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseReviewDTO
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelReviewCourse @Inject constructor(private val repo: CourseRepo) : ViewModel() {

    private val _courseReviewData: MutableLiveData<List<CourseReviewDTO>> = MutableLiveData()
    val courseReviewData: LiveData<List<CourseReviewDTO>> = _courseReviewData



    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getCourseReview(reviewId:Int,token: String) {
        viewModelScope.launch {
            repo.getCourseReview(reviewId,token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        val res = data.courseReviewDTOs
                         Log.d("+++++ResultData", res.toString())
                        _courseReviewData.postValue(res)
                    }

                }
            }
        }
    }
}