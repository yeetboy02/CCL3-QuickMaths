package com.cc221010.quickmaths.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class mainViewModel(): ViewModel() {
    private val _mainViewState = MutableStateFlow(mainViewState());
    val mainViewState: StateFlow<mainViewState> = _mainViewState.asStateFlow();
}