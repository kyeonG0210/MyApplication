package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch13_activity.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 각각의 Activity가 자신들의 화면으로 구성되어 있음
        val binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //(시작하자마자) 값을 받아서 ratingbar 값 초기화
        val scores = intent.getFloatArrayExtra("myscore") //get과 Extra 사이에는 type이 들어가야 //name에는 putExtra name과 반드시 동일해야
        // 버튼 누르기 전에 초기화 됨
        binding.rbar1.rating = scores!![0] // 값이 null이 아님(!!)
        binding.rbar2.rating = scores!![1]
        binding.rbar3.rating = scores!![2]

        binding.btnReturn.setOnClickListener {
            // 현재의 rating bar에 있는 점수 값을 담아가서 전달
            scores[0] = binding.rbar1.rating
            scores[1] = binding.rbar2.rating
            scores[2] = binding.rbar3.rating
            intent.putExtra("result", scores) //돌아가기 버튼을 눌렀을 때 값을 돌려줌
            //        올바르게 리턴, 인텐트 전달
            setResult(RESULT_OK, intent) //* mainactivity로 넘어가고 스스로 종료 (MainActivity의 requestLauncher의 StartActivityForReasult로 돌아감)
            finish()
        }


    }
}