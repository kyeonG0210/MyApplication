package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch13_activity.databinding.ActivityMainBinding
import com.example.ch13_activity.databinding.ActivityScoreBinding

class MainActivity : AppCompatActivity() {
    var scores:FloatArray? = null // 전역변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 각각의 Activity가 자신들의 화면으로 구성되어 있음
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scores = savedInstanceState?.let{
            it.getFloatArray("datas")
        }
            ?:let{
            floatArrayOf(1.0f, 2.0f, 3.0f) //float, double 혼동 소지 있기 때문에 f 표시
            }
        //plot을 세 개의 값을 한 번에 배열 형태로 전달
        binding.tvScore1.text = scores!![0].toString()
        binding.tvScore2.text = scores!![1].toString()
        binding.tvScore3.text = scores!![2].toString()
        
        val requestLauncher:ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ //requestLauncher의 StartActivityForResult()로 되돌아옴
                //null일 수도 있으므로 '?' / get과 Extra 사이에 type이 들어가야 함
                val result = it.data?.getFloatArrayExtra("result")
                scores = result
                binding.tvScore1.text = result!![0].toString()
                binding.tvScore2.text = result!![1].toString()
                binding.tvScore3.text = result!![2].toString()
            }

        // button을 눌렀을 때 넘어감
        binding.btnScore.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("myscore",scores) // 호출
            // startActivity(intent)
            requestLauncher.launch(intent) //불리어진 애로 부터 값을 전달받을 수 있음
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { // 앱이 실제로 종료된 것이 아닌데 oncreate가 실행되었을 경우
        super.onSaveInstanceState(outState)

        //score 값을 저장
        outState.putFloatArray("datas", scores)
    }
}