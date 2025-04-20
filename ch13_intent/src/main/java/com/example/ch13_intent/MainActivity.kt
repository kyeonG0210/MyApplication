package com.example.ch13_intent
// 구글 앱 호출
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch13_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //6개의 클릭 리스너 생성

        binding.btnPhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //Intent : 하나 또는 두 개의 매개변수 => action, data
            startActivity(intent) //intent 부름 (!잊지 않기)
        }

        binding.btnDial.setOnClickListener { //전화 걸기 액션 뿐 아니라 전화번호를 데이터로 전달해줘야 함
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
//            val intent = Intent(Intent.ACTION_DIAL)
            val intent = Intent(Intent.ACTION_DIAL) //(ACTION_CALL로 했더니 오류 나서 DIAL로 변경함)
            intent.data = Uri.parse("tel:01012345678") //프로토콜 이름이 tel
            startActivity(intent)
        }

        binding.btnGMap.setOnClickListener {
            // 위도, 경도
            val lat = 37.651450
            val lon = 127.01667
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + "," + lon)) //프로토콜 이름 geo
            startActivity(intent)
        }

        binding.btnWeb.setOnClickListener {
            // 자동으로 웹 브라우저 불리게 됨
            // 에뮬레이터 환경에서 크롬 브라우저를 사용할 때 컴퓨터가 멈추는 오류 발생. (다른 브라우저 설치 후 디바이스 기본 브라우저로 설정해야 정상 동작함) 실습 때 이 부분은 에뮬레이터 X
            // val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.duksung.ac.kr"))
            // startActivity(intent)
        }

        binding.btnSearch.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            // google search를 이용하기 때문에 특별하게 url 등 형태의 데이터 줄 필요 없음.
            // 검색어를 초기값으로 줌
                            //저장할 변수의 이름    //검색하고자 하는 문장(값)
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            startActivity(intent)
        }

        binding.btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-1234-5678"))
            intent.putExtra("sms_body", "안녕하세요") //여기까지 intent '만드는' 과정
            startActivity(intent) //실제로 intent를 다른 앱을 호출하기 위해서는 startActivity를 불러야 함
        }

    }
}
