package com.asmanmirza.schoolpen.UI.Student.Courses.Models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelReviewCourse
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import javax.inject.Inject

class CourseViewModelFactory @Inject constructor(private val repo: CourseRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelCourse(repo) as T
    }
}
