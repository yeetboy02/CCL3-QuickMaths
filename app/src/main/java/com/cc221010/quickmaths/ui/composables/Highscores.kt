package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221010.quickmaths.R
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.ui.mainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Highscores(mainViewModel:mainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();

    LazyColumn() {
        items(state.value.scores) {
            val rowModifier:Modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .clickable { mainViewModel.openEditModal(it.id) }
            val latestModifier = if (it.id == state.value.lastScore!!.id) {
                    rowModifier.border(4.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10));
                }
                else {
                    rowModifier;
                }
            Row(
                modifier = latestModifier,
                horizontalArrangement = Arrangement.Center,
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 5.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.2f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = (state.value.scores.indexOf(it) + 1).toString() + ".",
                                fontSize = 40.sp,
                                fontWeight = FontWeight(1000),
                                color = MaterialTheme.colorScheme.background,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = MaterialTheme.colorScheme.tertiary,
                                        offset = Offset(0f, 5f),
                                        blurRadius = 0f
                                    )
                                )
                            );
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .fillMaxHeight()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = it.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.background,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            offset = Offset(0f, 5f),
                                            blurRadius = 0f
                                        )
                                    )
                                );
                                Icon(
                                    modifier = Modifier
                                        .width(37.dp)
                                        .height(27.dp)
                                        .padding(start = 10.dp, top = 3.dp),
                                    painter = painterResource(id = R.drawable.edit),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = it.points.toString(),
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.background,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            offset = Offset(0f, 5f),
                                            blurRadius = 0f
                                        )
                                    )
                                );
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = it.date!!.format(dateFormat),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.background,
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            offset = Offset(0f, 5f),
                                            blurRadius = 0f
                                        )
                                    )
                                );
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = { mainViewModel.openDeleteModal(it.id) },
                                    modifier = Modifier
                                        .width(64.dp)
                                        .height(64.dp),
                                    shape = RoundedCornerShape(20),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.delete),
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(40.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    editModal(mainViewModel);
    deleteModal(mainViewModel);
}