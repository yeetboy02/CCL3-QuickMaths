package com.cc221010.quickmaths.ui.composables

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221010.quickmaths.ui.gameViewModel
import com.cc221010.quickmaths.ui.mainViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game(mainViewModel:mainViewModel, gameViewModel:gameViewModel, navController:NavController) {
    val gameState = gameViewModel.gameViewState.collectAsState();

    var answer by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(""))}
    var answerCorrect:Boolean? by remember { mutableStateOf(null) }


    if (gameState.value.currQuestion !== null) {

        // TIMER
        var time by remember { mutableStateOf(0L) };
        var isRunning by remember { mutableStateOf(true) };
        var startTime by remember { mutableStateOf(System.currentTimeMillis()) }

        // POINTS
        var pointsPerStep by remember { mutableStateOf(gameState.value.currQuestion!!.maxPoints / 120 ) };
        var currPoints by remember { mutableStateOf(gameState.value.currQuestion!!.maxPoints) };

        Column() {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Total Points: " + gameState.value.totalPoints.toString())
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth().height(123.dp).padding(start = 25.dp, end = 25.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row() {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text(text = "Points")
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Time")
                            }
                        }
                        Row() {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Text(text = currPoints.toString())
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = formatTime(timeInMillis = time))
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(222.dp)
                        .padding(start = 25.dp, end = 25.dp),
                ) {
                    Column() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Question " + (gameState.value.questionsAnswered + 1).toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = gameState.value.currQuestion!!.asString);
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (answerCorrect == null) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = answer,
                        placeholder = { Text(text="Answer") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done , keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                isRunning = false;
                                answerCorrect = (answer.text.toInt() == gameState.value.currQuestion!!.result);
                            }
                        ),
                        onValueChange = { newText ->
                            if (newText.text.length <= 30) {
                                answer = newText
                            }
                        },
                    )
                }
                else {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = (if (answerCorrect as Boolean) "Correct: " + answer.text else "Wrong: " + answer.text))
                        }
                        Button(
                            onClick = {
                                if (answerCorrect as Boolean) {
                                    gameViewModel.questionAnswered(currPoints);
                                    navController.navigate(Screen.Game.route);
                                }
                                else {

                                }
                            }
                        ) {
                            Text(text = "Continue")
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
                }
                else if (currPoints > gameState.value.currQuestion!!.minPoints) {
                    currPoints = gameState.value.currQuestion!!.minPoints;
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