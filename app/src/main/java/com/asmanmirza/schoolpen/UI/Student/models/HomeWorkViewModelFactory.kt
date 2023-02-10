package com.asmanmirza.schoolpen.UI.Student.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.HomeWorkSchool
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHomework
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.repository.HomeworkRepository
import javax.inject.Inject

class HomeWorkViewModelFactory @Inject constructor(private val repo: HomeworkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelHomework(repo) as T
    }
}