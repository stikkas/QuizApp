package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.data.model.getQuestion
import com.example.quizapp.data.model.isLastQuestion
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizQuestionsBinding.inflate(layoutInflater)
    }
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setQuestion()
    }

    private fun setQuestion() {
        val question = getQuestion(currentPosition)
        setDefaultOptions()
        val pos = currentPosition + 1
        with(binding) {
            progressBar.progress = pos
            btnSubmit.text = if (isLastQuestion(currentPosition)) {
                "FINISH"
            } else {
                "SUBMIT"
            }
            tvProgress.text = resources.getString(R.string.progress_Text, pos, progressBar.max)
            tvQuestion.text = question.value
            ivFlag.setImageResource(question.image)
            val answers = question.answers
            tvOptionOne.text = answers[0].value
            tvOptionTwo.text = answers[1].value
            tvOptionThree.text = answers[2].value
            tvOption4.text = answers[3].value
        }
    }

    private fun setDefaultOptions() {
        with(binding) {
            setDefaultOptionView(tvOptionOne)
            setDefaultOptionView(tvOptionTwo)
            setDefaultOptionView(tvOptionThree)
            setDefaultOptionView(tvOption4)
        }
    }

    private fun setDefaultOptionView(option: TextView) {
        option.setTextColor(Color.parseColor("#7A8089"))
        option.typeface = Typeface.DEFAULT
        option.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.default_option_border_bg
        )
    }
}