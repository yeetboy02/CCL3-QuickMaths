package com.cc221010.quickmaths.ui.composables

import androidx.compose.material.Icon
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cc221010.quickmaths.R
import com.cc221010.quickmaths.data.dateFormat
import com.cc221010.quickmaths.ui.mainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(mainViewModel:mainViewModel, navController:NavController) {
    val state = mainViewModel.mainViewState.collectAsState();

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(109.dp)
        .padding(top = 40.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text="QuickMaths",
                    fontSize = 38.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White,
                    modifier = Modifier.padding(start = 25.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "QuickMaths",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .height(69.dp)
                        .padding(end = 25.dp))
            }
        }
    }

    if (state.value.lastScore != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 175.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.last_score) + ":",
                modifier = Modifier.padding(bottom = 20.dp),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 30.sp
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                border = BorderStroke(4.dp, MaterialTheme.colorScheme.tertiary),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.lastScore!!.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(top = 10.dp),
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.lastScore!!.points.toString() + " " + stringResource(R.string.points),
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.lastScore!!.date!!.format(dateFormat),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(bottom = 15.dp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.tertiary,
                                offset = Offset(0f, 3f),
                                blurRadius = 0f
                            )
                        )
                    );
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 450.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            shape = RoundedCornerShape(35),
        ) {
            Button(
                onClick = { navController.navigate(Screen.Game.route) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(35),
                modifier = Modifier
                    .width(243.dp)
                    .height(115.dp)
                    .padding(bottom = 10.dp),
            ) {
                Text(
                    text = stringResource(R.string.new_game),
                    fontSize = 35.sp,
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