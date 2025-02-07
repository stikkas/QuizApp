package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.data.CORRECT_ANSWERS
import com.example.quizapp.data.TOTAL_QUESTIONS
import com.example.quizapp.data.USER_NAME
import com.example.quizapp.data.model.correctPosition
import com.example.quizapp.data.model.getQuestion
import com.example.quizapp.data.model.isLastQuestion
import com.example.quizapp.data.model.totalQuestions
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityQuizQuestionsBinding.inflate(layoutInflater)
    }

    private var currentPosition: Int = 0
    private var selectedOption: Int = 0
    private lateinit var userName: String
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setQuestion()
        with(binding) {
            tvOptionOne.setOnClickListener(this@QuizQuestionsActivity)
            tvOptionTwo.setOnClickListener(this@QuizQuestionsActivity)
            tvOptionThree.setOnClickListener(this@QuizQuestionsActivity)
            tvOption4.setOnClickListener(this@QuizQuestionsActivity)
            btnSubmit.setOnClickListener(this@QuizQuestionsActivity)
        }
        userName = intent.getStringExtra(USER_NAME)!!
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

    private fun selectedOptionView(view: TextView, num: Int) {
        setDefaultOptions()
        selectedOption = num
        view.setTextColor(Color.parseColor("#363A43"))
        view.setTypeface(view.typeface, Typeface.BOLD)
        view.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, view: Int) {
        with(binding) {
            when (answer) {
                1 -> tvOptionOne.background =
                    ContextCompat.getDrawable(this@QuizQuestionsActivity, view)

                2 -> tvOptionTwo.background =
                    ContextCompat.getDrawable(this@QuizQuestionsActivity, view)

                3 -> tvOptionThree.background =
                    ContextCompat.getDrawable(this@QuizQuestionsActivity, view)

                4 -> tvOption4.background =
                    ContextCompat.getDrawable(this@QuizQuestionsActivity, view)
            }
        }
    }

    override fun onClick(view: View) {
        with(binding) {
            when (view) {
                tvOptionOne -> selectedOptionView(tvOptionOne, 1)
                tvOptionTwo -> selectedOptionView(tvOptionTwo, 2)
                tvOptionThree -> selectedOptionView(tvOptionThree, 3)
                tvOption4 -> selectedOptionView(tvOption4, 4)
                btnSubmit -> if (selectedOption == 0) {
                    currentPosition++
                    when {
                        correctPosition(currentPosition) -> setQuestion()
                        else -> {
                            val intent =
                                Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
                            intent.putExtra(USER_NAME, userName)
                            intent.putExtra(CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(TOTAL_QUESTIONS, totalQuestions())
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = getQuestion(currentPosition)
                    if (question.answers[selectedOption - 1].correct) {
                        correctAnswers++
                    } else {
                        answerView(selectedOption, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.answers.indexOfFirst { it.correct } + 1,
                        R.drawable.correct_option_border_bg)
                    selectedOption = 0
                    btnSubmit.text = if (isLastQuestion(currentPosition)) {
                        "FINISH"
                    } else {
                        "GO TO NEXT QUESTION"
                    }
                }
            }
        }
    }
}