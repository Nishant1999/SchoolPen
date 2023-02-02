package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class NotesViewModel: ViewModel() {

    private var _notes = MutableStateFlow(NotesDataModel("",""))
    val notes = _notes.asStateFlow()

    fun addNote(data: NotesDataModel){
     _notes.value = data

    }
}