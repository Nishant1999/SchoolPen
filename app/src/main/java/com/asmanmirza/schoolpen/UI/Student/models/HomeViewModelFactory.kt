package com.asmanmirza.schoolpen.UI.Student.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.repository.UserDetailsRepo
import javax.inject.Inject


class HomeViewModelFactory @Inject constructor(private val repo: HomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelHome(repo) as T
    }
}




