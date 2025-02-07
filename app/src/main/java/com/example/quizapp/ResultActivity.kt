package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.data.CORRECT_ANSWERS
import com.example.quizapp.data.TOTAL_QUESTIONS
import com.example.quizapp.data.USER_NAME
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            tvName.text = intent.getStringExtra(USER_NAME)
            val totalQuestions = intent.getIntExtra(TOTAL_QUESTIONS, 0)
            val correctAnswers = intent.getIntExtra(CORRECT_ANSWERS, 0)
            tvScore.text = resources.getString(R.string.score, correctAnswers, totalQuestions)
            btnFinish.setOnClickListener {
                startActivity(Intent(this@ResultActivity, MainActivity::class.java))
            }
        }
    }
}