package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cc221010.quickmaths.data.models.score
import com.cc221010.quickmaths.ui.gameViewModel
import com.cc221010.quickmaths.ui.mainViewModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddScore(mainViewModel:mainViewModel, gameViewModel:gameViewModel, navController:NavController) {
    val gameState = gameViewModel.gameViewState.collectAsState();

    val keyboardController = LocalSoftwareKeyboardController.current;

    val totalPoints by remember { mutableStateOf(gameState.value.totalPoints) };
    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) };
    var nameEmptyError by remember { mutableStateOf(false) };

    gameViewModel.resetGame();

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "Save Score",
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().height(180.dp).padding(start = 25.dp, top = 30.dp, end = 25.dp),
                backgroundColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20),
                border = BorderStroke(4.dp, MaterialTheme.colorScheme.tertiary)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = totalPoints.toString() + " Points",
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
                    Row() {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp, end = 20.dp),
                            backgroundColor = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(20)
                        ) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(73.dp)
                                    .padding(bottom = 5.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    cursorColor = MaterialTheme.colorScheme.tertiary
                                ),
                                value = name,
                                placeholder = {
                                    Text(
                                        text = "Name",
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
                                textStyle = TextStyle(
                                    color = MaterialTheme.colorScheme.background,
                                    shadow = Shadow(
                                        color = MaterialTheme.colorScheme.tertiary,
                                        offset = Offset(0f, 5f),
                                        blurRadius = 0f
                                    ),
                                    fontSize = 25.sp
                                ),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        if (name.text.isEmpty()) {
                                            nameEmptyError = true;
                                        }
                                        else {
                                            nameEmptyError = false;
                                            mainViewModel.addScore(score(name = name.text, points = totalPoints, date = LocalDateTime.now()));
                                            navController.navigate(Screen.Highscores.route);
                                        }
                                    }
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(20),
                                onValueChange = { newText ->
                                    if (newText.text.length <= 30) {
                                        name = newText
                                    }
                                },
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
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
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 35.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (name.text.isEmpty()) {
                        nameEmptyError = true;
                    }
                    else {
                        keyboardController?.hide();
                        nameEmptyError = false;
                        mainViewModel.addScore(score(name = name.text, points = totalPoints, date = LocalDateTime.now()));
                        navController.navigate(Screen.Highscores.route);
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
                    text = "AddScore",
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
