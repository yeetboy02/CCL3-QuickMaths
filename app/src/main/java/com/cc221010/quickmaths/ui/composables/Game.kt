package com.cc221010.quickmaths.ui.composables

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cc221010.quickmaths.ui.gameViewModel
import com.cc221010.quickmaths.ui.mainViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(gameViewModel:gameViewModel, navController:NavController) {
    val gameState = gameViewModel.gameViewState.collectAsState();

    var answer by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(""))}
    var answerCorrect:Boolean? by remember { mutableStateOf(null) }


    if (gameState.value.currQuestion !== null) {

        // TIMER
        var time by remember { mutableStateOf(0L) };
        var isRunning by remember { mutableStateOf(true) };
        var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

        // POINTS
        var pointsPerStep by remember { mutableStateOf(gameState.value.currQuestion!!.maxPoints / 120) };
        var currPoints by remember { mutableStateOf(gameState.value.currQuestion!!.maxPoints) };

        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp, bottom = 12.dp)
            ) {
                Text(
                    text = "Total Points: " + gameState.value.totalPoints.toString(),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Transparent)
                    .shadow(elevation = 5.dp)
            ) {

            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth().height(130.dp)
                        .padding(start = 25.dp, end = 25.dp),
                    shape = RoundedCornerShape(20),
                    backgroundColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().height(123.dp).padding(bottom = 7.dp),
                        shape = RoundedCornerShape(20),
                        backgroundColor = MaterialTheme.colorScheme.primary
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Points",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(400),
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Time",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(400),
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }
                            }
                            Row() {
                                Column(
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = currPoints.toString(),
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight(700),
                                        color = MaterialTheme.colorScheme.background
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = formatTime(timeInMillis = time),
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight(700),
                                        color = MaterialTheme.colorScheme.background
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(222.dp)
                        .padding(start = 25.dp, end = 25.dp),
                    border = BorderStroke(4.dp, MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(20),
                    backgroundColor = MaterialTheme.colorScheme.secondary
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(bottom = 30.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Question " + (gameState.value.questionsAnswered + 1).toString(),
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight(700),
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
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 15.dp),
                                text = gameState.value.currQuestion!!.asString,
                                fontSize = 25.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight(700),
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
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 25.dp, end = 25.dp, top = 25.dp)
            ) {
                if (answerCorrect == null) {
                    Card(
                        shape = RoundedCornerShape(35),
                        backgroundColor = MaterialTheme.colorScheme.tertiary
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(73.dp)
                                .padding(bottom = 5.dp),
                            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.primary),
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.background,
                                shadow = Shadow(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    offset = Offset(0f, 5f),
                                    blurRadius = 0f
                                ),
                                fontSize = 30.sp
                            ),
                            shape = RoundedCornerShape(35),
                            value = answer,
                            placeholder = {
                                Text(
                                    text = "Answer...",
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
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    isRunning = false;
                                    answerCorrect =
                                        ((answer.text.toIntOrNull()) == gameState.value.currQuestion!!.result);
                                }
                            ),
                            onValueChange = { newText ->
                                if (newText.text.length <= 30) {
                                    answer = newText
                                }
                            },
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.height(95.dp),
                            shape = RoundedCornerShape(20),
                            backgroundColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                                backgroundColor = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(20)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(0.5f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Your Answer",
                                            fontSize = 20.sp,
                                            color = MaterialTheme.colorScheme.tertiary,
                                            fontWeight = FontWeight(600)
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                        ) {
                                            Text(
                                                text = answer.text,
                                                fontSize = 25.sp,
                                                color = MaterialTheme.colorScheme.background,
                                                fontWeight = FontWeight(700),
                                                style = TextStyle(
                                                    shadow = Shadow(
                                                        color = MaterialTheme.colorScheme.tertiary,
                                                        offset = Offset(0f, 5f),
                                                        blurRadius = 0f
                                                    )
                                                )
                                            )
                                            if (answerCorrect as Boolean) {
                                                Icon(
                                                    modifier = Modifier.height(37.dp),
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = "",
                                                    tint = MaterialTheme.colorScheme.onError
                                                )
                                            }
                                            else {
                                                Icon (
                                                    modifier = Modifier.height(37.dp),
                                                    imageVector = Icons.Default.Clear,
                                                    contentDescription = "",
                                                    tint = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }
                                    }
                                    if (answerCorrect as Boolean == false) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text =  "Correct Answer",
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.tertiary,
                                                fontWeight = FontWeight(600)
                                            )
                                            Text(
                                                text = gameState.value.currQuestion!!.result.toString(),
                                                fontSize = 25.sp,
                                                color = MaterialTheme.colorScheme.background,
                                                fontWeight = FontWeight(700),
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
                        Card(
                            modifier = Modifier.padding(top = 30.dp),
                            backgroundColor = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(35),
                        ) {
                            Button(
                                onClick = {
                                    if (answerCorrect as Boolean) {
                                        gameViewModel.questionAnswered(currPoints);
                                        navController.navigate(Screen.Game.route);
                                    } else {
                                        navController.navigate(Screen.AddScore.route);
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(35),
                                modifier = Modifier
                                    .width(243.dp)
                                    .height(95.dp)
                                    .padding(bottom = 10.dp),
                            ) {
                                Text(
                                    text = "Continue",
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
            }
            LaunchedEffect(isRunning) {
                while (isRunning) {
                    delay(500);
                    time = System.currentTimeMillis() - startTime;
                    if (currPoints - pointsPerStep >= gameState.value.currQuestion!!.minPoints) {
                        currPoints -= pointsPerStep;
                    } else if (currPoints > gameState.value.currQuestion!!.minPoints) {
                        currPoints = gameState.value.currQuestion!!.minPoints;
                    }
                }
            }
        }
    }
}

@Composable
fun formatTime(timeInMillis:Long):String {
    val min = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60;
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60;

    return String.format("%02d:%02d", min, sec);
}