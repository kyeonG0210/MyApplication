package com.example.ch10_dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ch10_dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"  // TAG 상수 선언
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d(TAG, "Date : $year 년 ${month+1} 월 $dayOfMonth 일")

                    binding.btnDate.text = "Date : $year 년 ${month+1} 월 $dayOfMonth 일"
                    binding.btnDate.textSize = 24f
                    binding.btnDate.setTextColor(Color.rgb(255,0,0))

                    Toast.makeText(application, "Date : $year 년 ${month+1} 월 $dayOfMonth 일", Toast.LENGTH_SHORT)
                }
            }, 2025, 4, 7).show() //2025-4-7
        }

        binding.btnTime.setOnClickListener {
            TimePickerDialog(this, object:TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.btnTime.text = "Time : $hourOfDay 시 $minute 분"
                    binding.btnTime.textSize = 24f
                    binding.btnTime.setTextColor(Color.rgb(255,0,0))
                }
            }, 16, 49, true).show()
        }
        val eventHandler = object.DialogInterface.OnClickListener {
            override fun OnClick(dialog: DialogInterface?, which:Int)
        }
        binding.btnAlert.setOnClickListener {
            /*
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("알림")
            dialog.setIcon(android.R.drawable.ic_dialog_alert)
            dialog.setMessage("정말로 종료하시겠습니까?")
            dialog.setPositiveButton("예", null)
            dialog.setNegativeButton("아니오", null)
            dialog.setNeutralButton("상세정보", null)
            dialog.show()
             */
            AlertDialog.Builder(this).run {
                setTitle("알림")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", DialogInterface.OnClickListener{
                    dialog, which -> Log.d(TAG, "POSITIVE BUTTON") 
                })
                setNegativeButton("아니오", DialogInterface.OnClickListener{
                        dialog, which -> Log.d(TAG, "NEGATIVE BUTTON")
                })
                setNeutralButton("상세정보", null)
                show()
            }
        }

        val items = arrayOf<String>("빨강", "초록", "파랑", "노랑")
        binding.btnItem.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("알림")
                setIcon(android.R.drawable.ic_dialog_alert)
                setItems(items, null)
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세정보", eventHandler)
                show()
        }

        binding.btnSingle.setOnClickListener {  }

        binding.btnMulti.setOnClickListener {  }

        binding.btnCustom.setOnClickListener {  }
    }
}