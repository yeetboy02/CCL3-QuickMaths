package com.cc221010.quickmaths.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cc221010.quickmaths.ui.mainViewModel

@Composable
fun deleteModal(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    if (state.value.deleteModalOpen) {
        AlertDialog(
            onDismissRequest = { mainViewModel.closeDeleteModal(false) },
            text = { Text(text = state.value.currEditScore!!.id.toString()) },
            buttons = { /*TODO*/ })
    }
}