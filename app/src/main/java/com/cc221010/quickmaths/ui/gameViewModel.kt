package com.cc221010.quickmaths.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cc221010.quickmaths.helpers.Calculation
import com.cc221010.quickmaths.helpers.NumberSequence
import com.cc221010.quickmaths.helpers.Question
import com.cc221010.quickmaths.helpers.getRandInt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class gameViewModel:ViewModel() {
    private val _gameViewState = MutableStateFlow(gameViewState());
    val gameViewState: StateFlow<gameViewState> = _gameViewState.asStateFlow();

    fun getQuestion() {
        val newQuestion:Question? = when (getRandInt(0, 1)) {
            1 -> Calculation();
            else -> NumberSequence();
        }
        _gameViewState.update {
            it.copy(currQuestion = newQuestion);
        }
    }

    fun addPoints(points:Int) {
        val newPoints:Int = gameViewState.value.totalPoints + points;
        _gameViewState.update {
            it.copy(totalPoints = newPoints);
        }
    }

    fun resetGame() {
        _gameViewState.update {
            it.copy(totalPoints = 0, currQuestion = null, questionsAnswered = 0)
        }
    }
}