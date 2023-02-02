package com.asmanmirza.schoolpen.UI.Teacher.Home.Models

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AssignedClassSharedViewModel : ViewModel() {

    var _showState = MutableStateFlow<ViewType>(ViewType.GRID)
    var showState = _showState.asStateFlow()

    fun changeLayout()  {
       if(_showState.value == ViewType.GRID){
           _showState.value = ViewType.LIST
       }
        else{
            _showState.value = ViewType.GRID
       }
    }

}

enum class ViewType {
    LIST, GRID
}