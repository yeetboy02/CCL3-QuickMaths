package com.cc221010.quickmaths.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cc221010.quickmaths.ui.mainViewModel

@Composable
fun editModal(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    if (state.value.editModalOpen) {
        AlertDialog(onDismissRequest = { mainViewModel.closeEditModal(false) }, buttons = { /*TODO*/ })
    }
}