package com.asmanmirza.schoolpen.UI.Student.Home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.databinding.ActivityCreateTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskActivity : AppCompatActivity() {

    lateinit var binding:ActivityCreateTaskBinding;
    private var clicked = 0;
    val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){
        binding.apply {

            val timePicker = TimePickerDialog(
                this@CreateTaskActivity,
                timePickerDialogListener,
                12,
                10,
                false
            )

            btnBack.setOnClickListener {
                finish()
            }

            startTime.setOnClickListener {
                clicked = 1;
                timePicker.show()
            }

            endTime.setOnClickListener {
                clicked = 2;
                timePicker.show()
            }

            datePick.setOnClickListener {
                DatePickerDialog(
                    this@CreateTaskActivity,
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        }
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.datePick.text = dateFormat.format(myCalendar.time)
    }

    var date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, day)
        updateLabel()
    }

    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> // logic to properly handle
            // the picked timings by user
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }
            if(clicked == 1){
                binding.startTime.text = formattedTime;
            }else{
                binding.endTime.text = formattedTime;
            }
        }
}