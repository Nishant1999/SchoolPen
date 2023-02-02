package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.databinding.FragmentAssignedClassNotesBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class AssignedClassNotesFragment : Fragment() {

    private var _binding : FragmentAssignedClassNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NotesViewModel
    var data : ArrayList<NotesDataModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAssignedClassNotesBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        data.add(NotesDataModel("Bring practical notebook to the class, we will conduct some experiments tomorrow.","today"))
        binding.notesRecyclerView.adapter = NotesAdapter(requireContext(),data )

        binding.notesSendButton.setOnClickListener {
            var note = binding.editTextNotes.text.toString()
            binding.editTextNotes.text.clear()
            viewModel.addNote(NotesDataModel(note,Calendar.getInstance().time.toString()))
        }
        lifecycleScope.launch {
            viewModel.notes.collect{
               if(it.note != ""){
                   updateData(it)
               }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(dataItem: NotesDataModel){
        data.add(dataItem)
        binding.notesRecyclerView.adapter!!.notifyDataSetChanged()
    }
}
