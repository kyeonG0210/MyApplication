// ScoreActivity는 사용자가 점수를 수정할 수 있는 화면이다.
// 여기서는 점수를 입력하는 RatingBar 위젯을 사용하여 점수를 수정하고, 이를 MainActivity로 반환하는 역할을 한다.
package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch13_activity.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    // 1. onCreate(): ScoreActivity의 UI를 설정, 전달받은 점수 데이터를 Rating바에 초기화함.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 각각의 Activity가 자신들의 화면으로 구성되어 있음
        val binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2. 점수 데이터 받기:
        // MainActivity에서 전달한 점수 데이터를 intent.getFloatArrayExtra("myscore")로 받는다.
        // 이를 RatingBar에 설정한다.

        //(시작하자마자) 값을 받아서 ratingbar 값 초기화
        val scores = intent.getFloatArrayExtra("myscore") //get과 Extra 사이에는 type이 들어가야 //name에는 putExtra name과 반드시 동일해야
        // 버튼 누르기 전에 초기화 됨
        binding.rbar1.rating = scores!![0] // 값이 null이 아님(!!)
        binding.rbar2.rating = scores!![1]
        binding.rbar3.rating = scores!![2]

        //3. btnReturn 클릭 시 점수 반환: 사용자가 btnReturn 버튼을 클릭하면 현재 RatingBar의 값을 읽어 scores 배열에 저장하고, 이를 Intent에 담아서 MainActivity로 반환
        binding.btnReturn.setOnClickListener {
            // 현재의 rating bar에 있는 점수 값을 담아가서 전달
            scores[0] = binding.rbar1.rating
            scores[1] = binding.rbar2.rating
            scores[2] = binding.rbar3.rating
            intent.putExtra("result", scores) //돌아가기 버튼을 눌렀을 때 값을 돌려줌
            //        올바르게 리턴, 인텐트 전달
            setResult(RESULT_OK, intent) // 결과를 반환함
            finish() //* mainactivity로 넘어가고 스스로 종료 (MainActivity의 requestLauncher의 StartActivityForReasult로 돌아감)
        }


    }
}

/*
동작 흐름:
1. **MainActivity**에서 점수(scores)를 설정하고, 버튼을 클릭하여 ScoreActivity를 시작합니다.

2. **ScoreActivity**는 전달받은 점수를 RatingBar에 표시하고, 사용자가 점수를 수정한 후 버튼을 클릭하여 수정된 점수를 MainActivity로 전달합니다.

3. **MainActivity**는 ActivityResultLauncher를 사용하여 ScoreActivity에서 돌아오는 결과를 받습니다. 결과를 받아 화면에 다시 표시합니다.

이렇게 **MainActivity**와 ScoreActivity 간에 데이터를 주고받으며, ActivityResultLauncher를 사용해 안전하게 결과를 처리하는 방식입니다.
*/