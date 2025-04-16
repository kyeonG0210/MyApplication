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
import com.example.ch10_dialog.databinding.DialogCustomBinding

class MainActivity : AppCompatActivity() {
    val TAG = "25_android_programming"  // TAG 상수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d(TAG, "Date : $year 년 ${month + 1} 월 $dayOfMonth 일")

                    binding.btnDate.text = "Date : $year 년 ${month + 1} 월 $dayOfMonth 일"
                    binding.btnDate.textSize = 24f
                    binding.btnDate.setTextColor(Color.rgb(255, 0, 0))

                    Toast.makeText(
                        application,
                        "Date : $year 년 ${month + 1} 월 $dayOfMonth 일",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, 2025, 4, 7).show() //2025-4-7
        }

        binding.btnTime.setOnClickListener {
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.btnTime.text = "Time : $hourOfDay 시 $minute 분"
                    binding.btnTime.textSize = 24f
                    binding.btnTime.setTextColor(Color.rgb(255, 0, 0))
                }
            }, 16, 49, true).show()
        }
        val eventHandler = object:DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d(TAG, "POSITIVE BUTTON")
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d(TAG, "NEGATIVE BUTTON")
                } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                    Log.d(TAG, "NEUTRAL BUTTON")
                }
            }
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
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세정보", eventHandler)
                show()
            }
        }

        val items = arrayOf<String>("빨강", "초록", "파랑", "노랑")
        binding.btnItem.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("알림")
                setIcon(android.R.drawable.ic_dialog_alert)
                setItems(items, object:DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        binding.btnItem.text = "Item : ${items[which]}"
                        binding.btnItem.textSize = 24f
                        binding.btnItem.setTextColor(Color.rgb(255, 0, 0))
                    }
                })
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세정보", eventHandler)
                show()
            }

            var selected = 2
            val eventHandler2 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d(TAG, "POSITIVE BUTTON")
                        binding.btnSingle.text = "Single Item : ${items[selected]}"
                        binding.btnSingle.textSize = 24f
                        binding.btnSingle.setTextColor(Color.rgb(255, 0, 0))
                    } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                        Log.d(TAG, "NEGATIVE BUTTON")
                    } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                        Log.d(TAG, "NEUTRAL BUTTON")
                    }
                }
            }

            binding.btnSingle.setOnClickListener {
                AlertDialog.Builder(this).run {
                    setTitle("알림")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setSingleChoiceItems(items, 2, object:DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            selected = which
                        }
                    })
                    setPositiveButton("예", eventHandler2)
                    setNegativeButton("아니오", eventHandler2)
                    setNeutralButton("상세정보", eventHandler2)
                    show()
                }
            }
            var multi_selected = booleanArrayOf(true, false, true, false)
            val eventHandler3 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d(TAG, "POSITIVE BUTTON")
                        var str = ""
                        for(i in 0..3) { // 0..3
                            if(multi_selected[i] == true)
                            str += items[i]
                        }
                        binding.btnMulti.text = "Multi Items : $str"
                        binding.btnMulti.textSize = 24f
                        binding.btnMulti.setTextColor(Color.rgb(255, 0, 0))
                    }
                    else if (which == DialogInterface.BUTTON_NEGATIVE) {
                        Log.d(TAG, "NEGATIVE BUTTON")
                    }
                    else if (which == DialogInterface.BUTTON_NEUTRAL) {
                        Log.d(TAG, "NEUTRAL BUTTON")
                    }
                }
            }
            binding.btnMulti.setOnClickListener {
                AlertDialog.Builder(this).run {
                    setTitle("알림")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object:DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                            Log.d(TAG, "$which 번째 항목 $isChecked")
                            multi_selected[which] = isChecked
                        }
                    })
                    setPositiveButton("예", eventHandler3)
                    setNegativeButton("아니오", eventHandler3)
                    setNeutralButton("상세정보", eventHandler3)
                    show()
                }
            }
            val dlg_binding = DialogCustomBinding.inflate(layoutInflater)
            val eventHandler4 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d(TAG, "POSITIVE BUTTON")
                        if(dlg_binding.rbtn1.isChecked == true) {
                            // binding.btnCustom.text = "1학년"
                            binding.btnCustom.text = dlg_binding.rbtn1.text.toString()
                        }
                        binding.btnCustom.textSize = 24f
                        binding.btnCustom.setTextColor(Color.rgb(255, 0, 0))
                    }
                }
            }
            val customDlg = AlertDialog.Builder(this).run {
                setTitle("알림")
                setView(dlg_binding.root)
                setPositiveButton("Yes", eventHandler4)
                create() //show가 연결되지 않는 경우 create 따로 넣어줘야 함
            }
            binding.btnCustom.setOnClickListener {
                //AlertDialog.Builder(this).run {
                //    setTitle("알림")
                //    setView(dlg_binding.root)
                //    setPositiveButton("Yes", null)
                //    show() //dialog 만들면 반드시 들어가야 함.
                //}
                customDlg.show()
            }
        }
    }
}