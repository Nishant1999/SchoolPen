package com.asmanmirza.schoolpen.UI.Student.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Student.repository.UserDetailsRepo
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repo: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelProfile(repo) as T
    }
}