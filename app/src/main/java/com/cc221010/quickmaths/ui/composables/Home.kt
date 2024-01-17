package com.cc221010.quickmaths.ui.composables

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.ui.mainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();

    if (state.value.lastScore != null) {
        Text(text = state.value.lastScore!!.date!!.format(dateFormat));
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            shape = RoundedCornerShape(35),
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(35),
                modifier = Modifier
                    .width(243.dp)
                    .height(115.dp)
                    .padding(bottom = 10.dp),
            ) {
                Text(
                    text = "New Game",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}