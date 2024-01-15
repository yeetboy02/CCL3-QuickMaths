package com.cc221010.quickmaths.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221010.quickmaths.ui.mainViewModel

sealed class Screen(val route: String){
    object Home: Screen("home");
    object Highscores: Screen("highscores");
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(mainViewModel: mainViewModel) {
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
                mainViewModel.selectScreen(Screen.Home)
            }
            composable(Screen.Highscores.route){
                mainViewModel.selectScreen(Screen.Highscores)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController:NavHostController, selectedScreen:Screen) {
    BottomNavigation () {
        NavigationBarItem(
            selected = (selectedScreen == Screen.Home),
            onClick = { navController.navigate(Screen.Home.route) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") })

        NavigationBarItem(
            selected = (selectedScreen == Screen.Highscores),
            onClick = { navController.navigate(Screen.Highscores.route) },
            icon = { Icon(imageVector = Icons.Default.Star, contentDescription = "") })

    }
}