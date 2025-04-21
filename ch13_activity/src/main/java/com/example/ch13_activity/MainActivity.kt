// MainActivity에서 ScoreActivity로 데이터를 보내고, ScoreActivity에서 수정된 데이터를 다시 MainActivity로 돌아와서 처리하는 방식

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
    // 1. scores는 사용자 점수에 대한 데이터를 저장하는 배열(FloatArray), 초기값은 1.0f, 2.0f, 3.0f
    var scores:FloatArray? = null // 전역 변수(onSaveInstanceState도 쓸 수 있게)

    // 2. onCreate(): Activity가 처음 생성될 때 호출됨.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 각각의 Activity가 자신들의 화면으로 구성되어 있음
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. 저장된 점수 불러오기. savedInstanceState는 앱이 일시 종료되거나 화면 회전 등의 상황에서 데이터를 복원할 수 있도록 도와줌.
        // 점수가 저장된 경우 이를 불러와서 화면에 표시
        //destroy되고 다시 create하기 직전에 저장해놓은 값을 씀
        scores = savedInstanceState?.let{
            it.getFloatArray("datas")
        }
            ?:let{ //초기값
            floatArrayOf(1.0f, 2.0f, 3.0f) //float, double 혼동 소지 있기 때문에 f 표시
            }
        // 4. 점수 표시(tvScore1, tvScore2, tvScore3에 scores 배열의 값들을 텍스트로)
        //plot을 세 개의 값을 한 번에 배열 형태로 전달
        binding.tvScore1.text = scores!![0].toString()
        binding.tvScore2.text = scores!![1].toString()
        binding.tvScore3.text = scores!![2].toString()

        //5. ActivityResultLauncher를 사용한 ScoreActivity 호출:
        // ActivityResultLauncher는 Activity가 결과를 반환할 때 안전,간편하게 처리하는 방법입니다.
        // 이 코드에서 ScoreActivity로 점수를 전달하고 결과를 받을 준비를 합니다.
        val requestLauncher:ActivityResultLauncher<Intent> = // 변수 생성
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ //requestLauncher의 StartActivityForResult()로 되돌아옴
                //null일 수도 있으므로 '?' / get과 Extra 사이에 type이 들어가야 함
                val result = it.data?.getFloatArrayExtra("result") //it = requestLauncher 자체를 의미하는 시스템 변수
                scores = result //항상 현재의 scores 값을 유지시킴
                binding.tvScore1.text = result!![0].toString() //값을 text에 넣어줌. float이니까 string으로 바꾸기
                binding.tvScore2.text = result!![1].toString()
                binding.tvScore3.text = result!![2].toString()
            }

        // 6. btnScore 클릭 시 ScoreActivity로 데이터 전달:
        // MainActivity의 버튼을 클릭하면 ScoreActivity로 점수를 전달하고,
        // requestLauncher.launch(intent)로 ScoreActivity를 실행.
        // button을 눌렀을 때 넘어감
        binding.btnScore.setOnClickListener {
            //                          누가 부르냐, 누구를 고르냐(내가 코틀린 코드로 구성하는 코드임을 표시 - ::class.java)
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("myscore",scores) // 호출
            // startActivity(intent) //ScoreActivity로부터 받을 수 없음
            requestLauncher.launch(intent) //불리어진 애로 부터 값을 전달받을 수 있음
        }
    }
    // 7. onSaveInstanceState(): 화면 회전 등의 이벤트로 인해 MainActivity가 재생성될 때, 현재 점수 상태를 저장하여 복원할 수 있게 함.
    override fun onSaveInstanceState(outState: Bundle) { // 앱이 실제로 종료된 것이 아닌데 oncreate가 실행되었을 경우(-레이아웃, 디바이스 방향이 바뀌는 경우)
        super.onSaveInstanceState(outState)

        //score 값을 저장         이름     변수
        outState.putFloatArray("datas", scores)
    }
}