package com.example.quizapp.data.model

import com.example.quizapp.R

private val questions = listOf(
    Question(
        1, "What country does the flag belong to?",
        R.drawable.ic_flag_of_argentina, listOf(
            Answer("Argentina", true), Answer("Australia"),
            Answer("Armenia"), Answer("Austria")
        )
    ),
    Question(
        2, "What country does the flag belong to?",
        R.drawable.ic_flag_of_australia, listOf(
            Answer("Angola"), Answer("Austria"),
            Answer("Australia", true), Answer("Armenia"),
        )
    ), Question(
        3, "What country does the flag belong to?",
        R.drawable.ic_flag_of_brazil, listOf(
            Answer("Belarus"), Answer("Belize"),
            Answer("Brunei"), Answer("Brazil", true)
        )
    ), Question(
        4, "What country does the flag belong to?",
        R.drawable.ic_flag_of_belgium, listOf(
            Answer("Bahamas"), Answer("Belgium", true),
            Answer("Barbados"), Answer("Belize")
        )
    ), Question(
        5, "What country does the flag belong to?",
        R.drawable.ic_flag_of_fiji, listOf(
            Answer("Gabon"), Answer("France"),
            Answer("Fiji", true), Answer("Finland")
        )
    ), Question(
        6, "What country does the flag belong to?",
        R.drawable.ic_flag_of_germany, listOf(
            Answer("Germany", true), Answer("Georgia"),
            Answer("Greece"), Answer("none of these")
        )
    ), Question(
        7, "What country does the flag belong to?",
        R.drawable.ic_flag_of_denmark, listOf(
            Answer("Dominica"), Answer("Egypt"),
            Answer("Denmark", true), Answer("Ephiopia")
        )
    ), Question(
        8, "What country does the flag belong to?",
        R.drawable.ic_flag_of_india, listOf(
            Answer("Ireland"), Answer("Iran"),
            Answer("Hungary"), Answer("India", true)
        )
    ), Question(
        9, "What country does the flag belong to?",
        R.drawable.ic_flag_of_new_zealand, listOf(
            Answer("Australia"), Answer("New Zealand", true),
            Answer("Tuvalu"), Answer("United States of America")
        )
    ), Question(
        10, "What country does the flag belong to?",
        R.drawable.ic_flag_of_kuwait, listOf(
            Answer("Kuwait", true), Answer("Jordan"),
            Answer("Sudan"), Answer("Palestine")
        )
    )
)

fun getQuestion(idx: Int): Question {
    return questions[idx.coerceAtLeast(0).coerceAtMost(questions.size - 1)]
}

fun isLastQuestion(idx: Int): Boolean {
    return idx == questions.size - 1
}