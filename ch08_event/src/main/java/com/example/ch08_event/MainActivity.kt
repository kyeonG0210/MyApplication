// 이벤트 발생 시 처리해주는 함수 = 콜백 함수

package com.example.ch08_event

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
// 뷰 이벤트 처리는 이벤트 소스와 이벤트 핸들러로 역할이 나뉘며, 이 둘을 리스너로 연결해야 이벤트 처리할 수 있음
    // 이벤트 소스: 이벤트가 발생한 객체
    // 이벤트 핸들러: 이벤트 발생 시 실행할 로직이 구현된 객체
    // 리스너: 이벤트 소스와 이벤트 핸들러를 연결해주는 함수
// 리스너를 선언해주는 순서는 중요하지 않음. (이벤트 발생 시 실행되는 핸들러를 '등록'하는 것이기 때문.)
    //          이벤트소스  리스너(이벤트 핸들러 등록)      해당 뷰(자신) **체크(체크/해제)**
        binding.chbStart.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked == true) { //선택되었을 때
                binding.choiceAnimal.visibility = View.VISIBLE //레이아웃 보여지게 함
            } else { //해제되었을 때
                binding.choiceAnimal.visibility = View.INVISIBLE //안 보이게 함
            }
        }
        // 어떤 radio를 누르고 '선택 완료'를 눌렀는 지에 따라 이미지를 바꿔줌
        binding.btnOk.setOnClickListener { //버튼의 클릭 이벤트 처리(별다른 return값이 없음)
            if (binding.rdDog.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.dog)
            } else if (binding.rdCat.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.cat)
            } else if (binding.rdRabbit.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.rabbit)
            }
        }

        binding.ivAnimal.setOnLongClickListener { //버튼의 롱클릭 이벤트 처리(return 값(boolean)을 갖는 이벤트 핸들러)
            // Log.d("tag", "ImageView LONG Click event")

            // 토스트: 화면 아래쪽에 잠깐 보였다가 사라지는 문자열
            //  - Toast의 makeText()함수로 만듦.
            // 첫번째 인자: 이 토스트가 어느 앱에 붙나? -> MainActivity 자기 자신 = this
            // 두번째 인자: 토스트에 띄울 메시지
            // 세번째 인자: 얼마동안 보여질 것이냐 (duration, LENGTH_LONG / LENGTH_SHORT)
            // Toast.makeText(this, "ImageView LONG Click event", Toast.LENGTH_LONG).show()
            val my_toast = Toast.makeText(this, "ImageView LONG Click event", Toast.LENGTH_LONG) //만들기(변수로 저장)
            my_toast.show() //보여주기

            true
        }
        // Radio button이 바뀔 때마다 동작(토스트 띄움)
        // checkedId -> 세 개의 radio 버튼 중에서 어떤 비디오 버튼이 선택되었는지를 전달해줌
        binding.rdGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rd_dog -> {Toast.makeText(this, "Dog 선택", Toast.LENGTH_SHORT).show()}
                R.id.rd_cat -> {Toast.makeText(this, "Cat 선택", Toast.LENGTH_SHORT).show()}
                R.id.rd_rabbit -> {Toast.makeText(this, "Rabbit 선택", Toast.LENGTH_SHORT).show()}
            }
        }
    }
}

    // 터치이벤트 콜백함수
    // 터치 이벤트가 발생할 때마다 onTouchEvent라고 하는 콜백함수를 부름
    // ACTION_DOWN: 화면을 손가락으로 누른 순간의 이벤트
    // ACTION_UP: 손가락 떼는 순간
    // ACTION_MOVE: 화면을 손가락으로 누른 채로 이동하는 순간의 이벤트

/*  override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){ // ? = event에 null값이 들어갈 수도 있다 //액션에 따라 구별해줄 수 있다
            MotionEvent.ACTION_DOWN -> Log.d("tag", "Touch Down ${event.x}, ${event.y}")
            MotionEvent.ACTION_UP -> Log.d("tag", "Touch Up Event")
        }
        return super.onTouchEvent(event)
    }

    // 키 이벤트 콜백함수
    // 키 이벤트: 사용자가 폰의 키를 누르는 순간에 발생
    // 콜백함수
    // - onKeyDown: 키를 누른 순간의 이벤트
    // - onKeyUp: 키를 떼는 순간의 이벤트
    // - onKeyLongPress: 키를 오래 누르는 순간의 이벤트

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("tag", "onKeyDown")
        //어떤 키가 눌려졌느냐에 따라 다르게 동작을 해줄 수 있다.
        when(keyCode) {
            KeyEvent.KEYCODE_A -> { Log.d("tag", "onKeyDown : A") } // A가 눌려졌을 때
            KeyEvent.KEYCODE_1 -> { Log.d("tag", "onKeyDown : 1") } // 1이 눌려졌을 때
            KeyEvent.KEYCODE_BACK -> { Log.d("tag", "onKeyDown : BACK") }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("tag", "onKeyUp")
        return super.onKeyUp(keyCode, event)
    }

}
*/