package com.example.ch06_view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ch06_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_main)

        // 뷰 바인딩 : 레이아웃 XML 파일에 선언한 뷰 객체를 코드에서 쉽게 이용하는 방법. 액티비티와 코틀린을 연결해주는 작업
        // gradle에서 android 괄호 안에 viewBinding enable = true, sync now 반드시 눌러줘야 함.
        // 자동으로 만들어지는 클래스의 이름은 레이아웃 XML 파일명을 따른다. (첫 글자를 대문자로 하고 밑줄(_)은 빼고 뒤에 오는 단어를 대문자로 만든 후 확장자 빼고 'Binding'을 추가)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //root 하나만 가지고 오면 root element 안에 들어가 있는 것은 다 따라 올라가게 되는 트리 구조를 가짐.

        // 개발자 모드에서 얼마만큼 진행되고 있는가를 출력하기 위해 쓰는 명령 => Log
        Log.d("tag", "start...") //debug mode

        //binding을 통해서 activity_main에 접근
        binding.progBar.visibility = View.INVISIBLE //보이지 않게 함

        binding.tvMain.text = "View Binding test" // text 변경
        binding.tvMain.textSize = 30.0F //실수
        // 모든 속성들이 멤버 변수 형태(visibility, text, textsize ...)형태로 제공 되는 것은 아님.
        // 제공이 안되는 것들은 함수 형태로 값을 바꿀 수 있음. -> setter 함수
        binding.tvMain.setTextColor(Color.rgb(100,150,255)) // 대표적인 color들 제공되고 있어서 사용하면 되지만 rgb 값을 쓸 수도 있음.

    }
}