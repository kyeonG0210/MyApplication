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
    val TAG = "25_android_programming"  // 별도로 String 변수를 줌.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            //첫 번째 인자: 누구의 Dialog인가?
            //두 번째 인자: 날짜를 선택했을 때 어떻게 처리해줄 것인가에 대한 부분. 이벤트 핸들러에 대한 값(함수 이름 없이 쓰는 object -> 이에 대한 함수는 DatePickerDialog에 있는 OnDateSetListener를 쓰겠다)
            //세 번째 인자: dialog가 처음 뜰 때 보여지는 초기 날짜(**월: 시작이 0이므로 4월 -> 3**)
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener { //Dialog를 만듦
                // OnDateSetListener에 의해 불리어지는 이벤트 핸들러의 이름: override에 있는 onDateSet (정해져 있음
                // 확인 버튼을 누르는 순간 onDateSet 함수가 자동으로 호출 됨
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d(TAG, "Date : $year 년 ${month + 1} 월 $dayOfMonth 일") //Log.d = debug시 이용 // month+1 (month: Int이기 때문)
                    //로그가 어느 패키지에 속하고 있는가를 의미할 때가 많음. 변수로 대신함.(변수로 관리하기 효율적)

                    //layout에 있는 view -> binding으로 접근 가능
                    binding.btnDate.text = "Date : $year 년 ${month + 1} 월 $dayOfMonth 일"
                    binding.btnDate.textSize = 24f
                    binding.btnDate.setTextColor(Color.rgb(255, 0, 0))

                    Toast.makeText(
                        applicationContext, "Date : $year 년 ${month + 1} 월 $dayOfMonth 일", Toast.LENGTH_SHORT
                    ).show()
                }
            }, 2025, 3, 7).show() //2025-4-7 //show() : **Dialog를 보여줌** (잊어버리지 않기!)
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
            // Alertdialog 만드는 첫 번째 방법 -> dialog 변수를 너무 많이 씀, 코드 길어짐
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("알림")
            dialog.setIcon(android.R.drawable.ic_dialog_alert)
            dialog.setMessage("정말로 종료하시겠습니까?")
            dialog.setPositiveButton("예", null) // null : "예"가 눌렸을 때 처리하는 핸들러 특별히 지정 안 함.
            dialog.setNegativeButton("아니오", null)
            dialog.setNeutralButton("상세정보", null)
            dialog.show() //마지막에는 반드시 show()
             */
            // Alertdialog 만드는 두 번째 방법 (더 간단)
            // AlertDialog.Builder에 의해 dialog 만듦, 이에 대한 설정을 한꺼번에 적용해주는 것 => run
            AlertDialog.Builder(this).run {
                setTitle("알림")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", eventHandler) // 버튼이 눌렸을 때 'eventHandler' 변수 실행
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세정보", eventHandler)
                show()
            }
        }

        val items = arrayOf<String>("빨강", "초록", "파랑", "노랑") //나열하고 싶은 item을 문장으로 4개 넣음
        binding.btnItem.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("알림")
                setIcon(android.R.drawable.ic_dialog_alert)
                setItems(items, object:DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        binding.btnItem.text = "Item : ${items[which]}" //which번째 있는 items 출력
                        binding.btnItem.textSize = 24f
                        binding.btnItem.setTextColor(Color.rgb(255, 0, 0))
                    }
                })
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세정보", eventHandler)
                show()
            }

            var selected = 2 // 바깥에서도 접근할 수 있는 변수(onclick 안에서 어디든지 접근 가능), 초기값(2 = 파랑)
            val eventHandler2 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) { //positive 버튼이 눌렸을 때만 선택된 값을 버튼에 보여줌
                        Log.d(TAG, "POSITIVE BUTTON")
                        binding.btnSingle.text = "Single Item : ${items[selected]}" //which 대신 selected를 넣어줌
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
                                                // 4개(0,1,2,3) 중 하나
                    setSingleChoiceItems(items, 2, object:DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            selected = which // 사용자가 alertdialog 밖에서 접근 못 하는 which 값에 있는 값을 selected에 넣어줌
                        }
                    })
                    setPositiveButton("예", eventHandler2)
                    setNegativeButton("아니오", eventHandler2)
                    setNeutralButton("상세정보", eventHandler2)
                    show()
                }
            }
            var multi_selected = booleanArrayOf(true, false, true, false) //아래 이벤트 핸들러에서 'multi_selected' 사용할 것이기 때문에 먼저 선언
            val eventHandler3 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d(TAG, "POSITIVE BUTTON")
                        var str = ""
                        for(i in 0..3) { // 0..3
                            if(multi_selected[i] == true) //체크 해제된 것은 제외
                            str += //for문이 돌아갈 때마다 str 변수에
                                items[i] //items에 있는 i번째 아이템을 출력
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
                    // multichoice의 경우 값을 넣어주는 것이 아니라 boolean array로 들어가는 4개의 값에 대해 각각 체크해줘야 함.
                    // true = 체크 O, false = 체크 X
                    setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object:DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) { // onClick의 매개변수는 which 뿐 아니라 isChecked
                            Log.d(TAG, "$which 번째 항목 $isChecked")
                            multi_selected[which] = isChecked // multi_selected의 which번째 변수에 isChecked에 의해 true/false 인지를 저장해두면 체크박스 4개에 대해 선택됨/안됨 저장할 수 있다.
                        }
                    })
                    setPositiveButton("예", eventHandler3)
                    setNegativeButton("아니오", eventHandler3)
                    setNeutralButton("상세정보", eventHandler3)
                    show()
                }
            }
            val dlg_binding = DialogCustomBinding.inflate(layoutInflater) //1. DialogCustom에 대한 binding 만듦
            val eventHandler4 = object:DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        Log.d(TAG, "POSITIVE BUTTON")
                        if(dlg_binding.rbtn1.isChecked == true) {
                            // binding.btnCustom.text = "1학년"
                            binding.btnCustom.text = dlg_binding.rbtn1.text.toString()
                            //2,3,4는 수업에서 생략함. 만들고 싶으면 만들기
                        }/* else if(dlg_binding.rbtn2.isChecked == true) {
                                binding.btnCustom.text = dlg_binding.rbtn2.text.toString()
                        } else if(dlg_binding.rbtn3.isChecked == true) {
                            binding.btnCustom.text = dlg_binding.rbtn3.text.toString()
                        } else if(dlg_binding.rbtn4.isChecked == true) {
                            binding.btnCustom.text = dlg_binding.rbtn4.text.toString()
                        }*/
                        binding.btnCustom.textSize = 24f
                        binding.btnCustom.setTextColor(Color.rgb(255, 0, 0))
                    }
                }
            }
            val customDlg = AlertDialog.Builder(this).run { //2. AlertDialog 만듦
                setTitle("알림")
                setView(dlg_binding.root) // dialog_cumtom의 layout에 따라 화면이 구성됨
                setPositiveButton("Yes", eventHandler4)
                create() //show가 연결되지 않는 경우 create 따로 넣어줘야 함
            } //버튼을 누를 때마다 만드는 것이 아니라 한 번만 만듦.
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