package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221010.quickmaths.R
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.data.models.score
import com.cc221010.quickmaths.ui.mainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun editModal(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    if (state.value.editModalOpen && state.value.currEditScore != null) {

        var name by rememberSaveable { mutableStateOf(state.value.currEditScore!!.name) }
        var nameEmptyError by remember { mutableStateOf(false) };

        AlertDialog(
            onDismissRequest = { mainViewModel.closeEditModal(false) },
            backgroundColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(305.dp)
                .border(4.dp, MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(20)),
            shape = RoundedCornerShape(20),
            text = {
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Edit Name",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.background,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    offset = Offset(0f, 5f),
                                    blurRadius = 0f
                                )
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column() {
                            Text(
                                text = "Enter new Name",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.padding(bottom = 7.dp),
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = MaterialTheme.colorScheme.tertiary,
                                        offset = Offset(0f, 5f),
                                        blurRadius = 0f
                                    )
                                )
                            )
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(73.dp)
                                    .clip(RoundedCornerShape(20)),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                                shape = RoundedCornerShape(20),
                            ) {
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(73.dp)
                                        .padding(bottom = 5.dp),
                                    colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primary),
                                    value = name,
                                    textStyle = TextStyle(
                                        color = MaterialTheme.colorScheme.background,
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            offset = Offset(0f, 5f),
                                            blurRadius = 0f
                                        ),
                                        fontSize = 30.sp
                                    ),
                                    maxLines = 1,
                                    shape = RoundedCornerShape(20),
                                    onValueChange = { newText ->
                                        if (newText.length <= 30) {
                                            name = newText
                                        }
                                    },
                                )
                            }
                            if (nameEmptyError) {
                                Text(
                                    text = "The name can't be empty!",
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            },
            buttons = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .width(180.dp)
                                .height(71.dp)
                                .clip(RoundedCornerShape(50)),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                            shape = RoundedCornerShape(50),
                        ) {
                            Button(
                                onClick = {
                                    if (name.isEmpty()) {
                                        nameEmptyError = true;
                                    }
                                    else {
                                        nameEmptyError = false;
                                        mainViewModel.closeEditModal(true, score(id = state.value.currEditScore!!.id, name = name, points = state.value.currEditScore!!.points, date = state.value.currEditScore!!.date));
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(71.dp)
                                    .padding(bottom = 5.dp),
                            ) {
                                Text(
                                    text = "Save Changes",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            offset = Offset(0f, 5f),
                                            blurRadius = 0f
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}