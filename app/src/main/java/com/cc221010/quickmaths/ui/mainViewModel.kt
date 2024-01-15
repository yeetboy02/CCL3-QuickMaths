package com.cc221010.quickmaths.ui

import androidx.lifecycle.ViewModel
import com.cc221010.quickmaths.ui.composables.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class mainViewModel(): ViewModel() {
    private val _mainViewState = MutableStateFlow(mainViewState());
    val mainViewState: StateFlow<mainViewState> = _mainViewState.asStateFlow();

    fun selectScreen(screen: Screen){
        _mainViewState.update { it.copy(selectedScreen = screen) }
    }
}