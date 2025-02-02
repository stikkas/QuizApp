package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.data.USER_NAME
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val startBtn = binding.btnStart
        val nameTxt = binding.etName

        startBtn.setOnClickListener {
            if (nameTxt.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
                intent.putExtra(USER_NAME, nameTxt.text.toString())
                startActivity(intent)
                finish()
            }
        }
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}