package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year  = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day   = myCalendar.get(Calendar.DAY_OF_MONTH)
        val currentYear  = year
        val currentMonth = month + 1
        val currentDay   = day
        val currentDate  = "$day/${month + 1}/$year"

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)
                val ageInMinutes = ageInMinutesCalculator(year, month, day, selectedYear, selectedMonth, selectedDayOfMonth)
                tvSelectedDateInMinutes.setText(ageInMinutes.toString())
            }, year, month, day).show()

    }

    fun ageInMinutesCalculator(theYear: Int, theMonth: Int, theDay: Int, selectedYear: Int, selectedMonth: Int, selectedDay: Int): Int {
        var leapYearsPassed = (theYear - selectedYear) / 4
        var extraLeapYearTime = leapYearsPassed * 1440
        var yearInMinutes = ((theYear - selectedYear) * 525600) + extraLeapYearTime
        var monthInMinutes = (theMonth - selectedMonth) * 43800
        var dayInMinutes = (theDay - selectedDay) * 1440

        var ageInMinutes = yearInMinutes + monthInMinutes + dayInMinutes

        return ageInMinutes
    }
}