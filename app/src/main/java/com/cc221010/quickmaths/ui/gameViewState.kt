package com.cc221010.quickmaths.ui

import com.cc221010.quickmaths.helpers.Question

data class gameViewState(
    val totalPoints:Int = 0,
    val currQuestion:Question? = null,
    val questionsAnswered:Int = 0,
)