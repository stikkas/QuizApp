package com.example.quizapp.data.model

data class Question(
    val id: Int,
    val value: String,
    val image: Int,
    val answers: List<Answer>
)
