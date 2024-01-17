package com.cc221010.quickmaths.ui

import com.cc221010.quickmaths.data.models.score
import com.cc221010.quickmaths.ui.composables.Screen

data class mainViewState(
    val currEditScore:score? = null,
    val lastScore:score? = null,
    val scores:List<score> = emptyList(),
    val selectedScreen:Screen = Screen.Home,
    val editModalOpen:Boolean = false,
    val deleteModalOpen:Boolean = false,
)