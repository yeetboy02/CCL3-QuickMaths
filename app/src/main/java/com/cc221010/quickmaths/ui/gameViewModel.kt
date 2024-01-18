package com.cc221010.quickmaths.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class gameViewModel:ViewModel() {
    private val _gameViewState = MutableStateFlow(gameViewState());
    val gameViewState: StateFlow<gameViewState> = _gameViewState.asStateFlow();
}