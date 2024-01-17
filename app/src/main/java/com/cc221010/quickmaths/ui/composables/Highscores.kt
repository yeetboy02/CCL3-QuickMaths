package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.ui.mainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Highscores(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();

    LazyColumn() {
        var index:Int = 1;
        items(state.value.scores) {
            Row(
                modifier = Modifier.clickable{ mainViewModel.openEditModal(it.id) }
            ) {
                Text(text = index.toString());
                Text(text = it.name);
                Text(text = it.points.toString());
                Text(text = it.date!!.format(dateFormat));
            }
            index++;
        }
    }

    editModal(mainViewModel);
}