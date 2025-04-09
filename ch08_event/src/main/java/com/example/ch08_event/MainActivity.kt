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

        binding.chbStart.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked == true) {
                binding.choiceAnimal.visibility = View.VISIBLE
            } else {
                binding.choiceAnimal.visibility = View.INVISIBLE
            }
        }

        binding.btnOk.setOnClickListener {
            if (binding.rdDog.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.dog)
            } else if (binding.rdCat.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.cat)
            } else if (binding.rdRabbit.isChecked) {
                binding.ivAnimal.setImageResource(R.drawable.rabbit)
            }
        }

        binding.ivAnimal.setOnLongClickListener {
            // Log.d("tag", "ImageView LONG Click event")
            // Toast.makeText(this, "ImageView LONG Click event", Toast.LENGTH_LONG).show()
            val my_toast = Toast.makeText(this, "ImageView LONG Click event", Toast.LENGTH_LONG)
            my_toast.show()

            true
        }

        binding.rdGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rd_dog -> {Toast.makeText(this, "Dog 선택", Toast.LENGTH_SHORT).show()}
                R.id.rd_cat -> {Toast.makeText(this, "Cat 선택", Toast.LENGTH_SHORT).show()}
                R.id.rd_rabbit -> {Toast.makeText(this, "Rabbit 선택", Toast.LENGTH_SHORT).show()}
            }
        }
    }
}

    //터치이벤트 콜백함수
/*  override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> Log.d("tag", "Touch Down ${event.x}, ${event.y}")
            MotionEvent.ACTION_UP -> Log.d("tag", "Touch Up Event")
        }
        return super.onTouchEvent(event)
    }

    // 키 이벤트 콜백함수
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("tag", "onKeyDown")
        when(keyCode) {
            KeyEvent.KEYCODE_A -> { Log.d("tag", "onKeyDown : A") }
            KeyEvent.KEYCODE_1 -> { Log.d("tag", "onKeyDown : 1") }
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