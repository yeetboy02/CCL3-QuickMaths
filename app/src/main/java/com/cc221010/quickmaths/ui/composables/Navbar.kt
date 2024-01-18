package com.cc221010.quickmaths.ui.composables

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221010.quickmaths.R
import com.cc221010.quickmaths.data.models.score
import com.cc221010.quickmaths.ui.gameViewModel
import com.cc221010.quickmaths.ui.mainViewModel
import java.time.LocalDate
import java.time.LocalDateTime

sealed class Screen(val route: String){
    object Home:Screen("home");
    object Highscores:Screen("highscores");
    object Game:Screen("game");
    object AddScore:Screen("addScore");
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarView(mainViewModel:mainViewModel, gameViewModel:gameViewModel) {
    val state = mainViewModel.mainViewState.collectAsState();
    val navController = rememberNavController();

    Scaffold(
        bottomBar = {BottomNavigationBar(navController, state.value.selectedScreen)}
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = Screen.Home.route
        ){
            composable(Screen.Home.route){
                mainViewModel.getLastScore();
                gameViewModel.resetGame();
                mainViewModel.selectScreen(Screen.Home);
                Home(mainViewModel = mainViewModel, navController = navController);
            }
            composable(Screen.Highscores.route){
                mainViewModel.getScores();
                gameViewModel.resetGame();
                mainViewModel.selectScreen(Screen.Highscores);
                Highscores(mainViewModel = mainViewModel);
            }
            composable(Screen.Game.route) {
                if (gameViewModel.gameViewState.collectAsState().value.currQuestion == null) {
                    gameViewModel.getQuestion();
                }
                mainViewModel.selectScreen(Screen.Game);
                Game(gameViewModel = gameViewModel, navController = navController);
            }
            composable(Screen.AddScore.route) {
                mainViewModel.selectScreen(Screen.AddScore);
                AddScore(mainViewModel = mainViewModel, gameViewModel = gameViewModel, navController = navController);
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController:NavHostController, selectedScreen:Screen) {
    if (selectedScreen == Screen.Home || selectedScreen == Screen.Highscores) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
        ) {
            BottomNavigation (
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 4.dp),
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                NavigationBarItem(
                    selected = (selectedScreen == Screen.Home),
                    onClick = { navController.navigate(Screen.Home.route) },
                    icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = "", modifier = Modifier
                        .width(45.dp)
                        .height(45.dp), tint = MaterialTheme.colorScheme.onPrimary) },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.tertiary),
                )
                NavigationBarItem(
                    selected = (selectedScreen == Screen.Highscores),
                    onClick = { navController.navigate(Screen.Highscores.route) },
                    icon = { Icon(painter = painterResource(id = R.drawable.highscore), contentDescription = "", modifier = Modifier
                        .width(45.dp)
                        .height(45.dp), tint = MaterialTheme.colorScheme.onPrimary) },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.tertiary),
                )
            }
        }
    }
}