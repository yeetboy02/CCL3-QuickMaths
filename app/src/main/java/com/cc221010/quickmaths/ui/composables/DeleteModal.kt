package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221010.quickmaths.R
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.ui.mainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun deleteModal(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    if (state.value.deleteModalOpen && state.value.currEditScore != null) {
        AlertDialog(
            onDismissRequest = { mainViewModel.closeDeleteModal(false) },
            backgroundColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.width(305.dp).border(4.dp, MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(20)),
            shape = RoundedCornerShape(20),
            text = {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Delete ",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    offset = Offset(0f, 5f),
                                    blurRadius = 0f
                                )
                            )
                        )
                        Text(
                            text = state.value.currEditScore!!.name,
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
                        Text(
                            text = "?",
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.tertiary,
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
                        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.value.currEditScore!!.points.toString() + " Points",
                            fontSize = 35.sp,
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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.value.currEditScore!!.date!!.format(dateFormat),
                            fontSize = 23.sp,
                            color = MaterialTheme.colorScheme.background,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    offset = Offset(0f, 5f),
                                    blurRadius = 0f
                                )
                            ))
                    }
                }
            },
            buttons = {
                Row (
                    modifier = Modifier.fillMaxWidth().height(84.dp).padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.width(136.dp).height(57.dp)
                                .clip(RoundedCornerShape(50)),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                            shape = RoundedCornerShape(50),
                        ) {
                            Button(
                                onClick = { mainViewModel.closeDeleteModal(true, state.value.currEditScore) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .width(136.dp)
                                    .height(57.dp)
                                    .padding(bottom = 5.dp),
                            ) {
                                Text(
                                    text = "Delete",
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
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.width(136.dp).height(57.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                            shape = RoundedCornerShape(50),
                        ) {
                            Button(
                                onClick = { mainViewModel.closeDeleteModal(false) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .width(136.dp)
                                    .height(57.dp)
                                    .padding(bottom = 5.dp),
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.tertiary,
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