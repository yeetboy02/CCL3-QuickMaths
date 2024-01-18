package com.cc221010.quickmaths.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddScore(mainViewModel:mainViewModel, gameViewModel:gameViewModel, navController:NavController) {
    val gameState = gameViewModel.gameViewState.collectAsState();

    val totalPoints by remember { mutableStateOf(gameState.value.totalPoints) };
    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) };
    var nameEmptyError by remember { mutableStateOf(false) };

    gameViewModel.resetGame();

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row() {
            Text(text = "Add Score")
        }
        Row() {
            Text(text = totalPoints.toString())
        }
        Row() {
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done , keyboardType = KeyboardType.Number),
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
            if (nameEmptyError) {
                Text(
                    text = "The name can't be empty!",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 15.sp
                )
            }
        }
        Row() {
            Button(
                onClick = {
                    if (name.text.isEmpty()) {
                        nameEmptyError = true;
                    }
                    else {
                        nameEmptyError = false;
                        mainViewModel.addScore(score(name = name.text, points = totalPoints, date = LocalDateTime.now()));
                        navController.navigate(Screen.Highscores.route);
                    }
                }
            ) {
                Text(text = "AddScore")
            }
        }
    }
}
