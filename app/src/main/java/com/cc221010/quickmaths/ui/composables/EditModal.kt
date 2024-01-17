package com.cc221010.quickmaths.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cc221010.quickmaths.ui.mainViewModel

@Composable
fun editModal(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    if (state.value.editModalOpen) {
        AlertDialog(
            onDismissRequest = { mainViewModel.closeEditModal(false) },
            text = { Text(text = state.value.currEditScore!!.id.toString()) },
            buttons = { /*TODO*/ })
    }
}