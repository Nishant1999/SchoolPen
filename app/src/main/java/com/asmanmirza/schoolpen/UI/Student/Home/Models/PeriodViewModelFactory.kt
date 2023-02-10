package com.asmanmirza.schoolpen.UI.Student.Home.Models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelPeriod
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.PeriodRepo
import javax.inject.Inject

class PeriodViewModelFactory @Inject constructor(private val repo: PeriodRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelPeriod(repo) as T
    }
}