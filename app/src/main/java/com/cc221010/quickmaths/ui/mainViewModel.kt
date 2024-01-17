package com.cc221010.quickmaths.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221010.quickmaths.data.models.score
import com.cc221010.quickmaths.data.scoreDAO
import com.cc221010.quickmaths.ui.composables.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class mainViewModel(private val dao: scoreDAO): ViewModel() {
    private val _mainViewState = MutableStateFlow(mainViewState());
    val mainViewState: StateFlow<mainViewState> = _mainViewState.asStateFlow();

    fun selectScreen(screen: Screen){
        _mainViewState.update { it.copy(selectedScreen = screen) }
    }

    fun getScores() {
        viewModelScope.launch {
            dao.getScores().collect(){
                    scores -> _mainViewState.update {
                it.copy(scores = scores)
            }
            };
        }
    }

    fun addScore(score: score) {
        viewModelScope.launch {
            dao.insertScore(score);
        }
    }
}