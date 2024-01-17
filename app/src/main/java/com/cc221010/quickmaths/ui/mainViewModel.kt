package com.cc221010.quickmaths.ui

import android.util.Log
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
            dao.getScores().collect() { scores ->
                _mainViewState.update {
                    it.copy(scores = scores);
                }
            };
        }
    }

    fun getScore(id:Int) {
        viewModelScope.launch {
            dao.getScore(id).collect() { score ->
                _mainViewState.update {
                    it.copy(currEditScore = score);
                }
            }
        }
    }

    fun getLastScore() {
        viewModelScope.launch {
            dao.getLastScore().collect() {
                score -> _mainViewState.update {
                    it.copy(lastScore = score);
            }
            }
        }
    }

    fun addScore(score: score) {
        viewModelScope.launch {
            dao.insertScore(score);
        }
    }

    fun openEditModal(id:Int) {
        getScore(id);
        _mainViewState.update {
            it.copy(editModalOpen = true);
        }
    }

    fun closeEditModal(edit:Boolean) {
        _mainViewState.update {
            it.copy(editModalOpen = false);
        }
    }

    fun openDeleteModal(id:Int) {
        _mainViewState.update {
            it.copy(deleteModalOpen = true);
        }
    }

    fun closeDeleteModal(delete:Boolean) {
        _mainViewState.update {
            it.copy(deleteModalOpen = false);
        }
    }
}