package com.example.ch13_intent

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

        binding.btnPhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //action, data
            startActivity(intent)
        }

        binding.btnDial.setOnClickListener {
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
//            val intent = Intent(Intent.ACTION_DIAL)
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:01012345678")
            startActivity(intent)
        }

        binding.btnGMap.setOnClickListener {
            // 위도, 경도
            val lat = 37.651450
            val lon = 127.01667
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + "," + lon))
            startActivity(intent)
        }

        binding.btnWeb.setOnClickListener {
            // val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.duksung.ac.kr"))
            // startActivity(intent)
        }

        binding.btnSearch.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            // 검색어를 초기값으로 줌
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            startActivity(intent)
        }

        binding.btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-1234-5678"))
            intent.putExtra("sms_body", "안녕하세요") //여기까지 intent '만드는' 과정
            startActivity(intent) //호출
        }

    }
}
