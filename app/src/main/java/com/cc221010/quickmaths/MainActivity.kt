package com.cc221010.quickmaths

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.cc221010.quickmaths.data.scoreDatabase
import com.cc221010.quickmaths.ui.composables.NavbarView
import com.cc221010.quickmaths.ui.gameViewModel
import com.cc221010.quickmaths.ui.mainViewModel
import com.cc221010.quickmaths.ui.theme.QuickMathsTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(this, scoreDatabase::class.java, "ScoreDatabase.db").build();
    }

    private val gameViewModel = gameViewModel() ;

    private val mainViewModel by viewModels<mainViewModel>(
        factoryProducer = {
            object:ViewModelProvider.Factory{
                override fun <T:ViewModel> create(modelClass:Class<T>):T{
                    return mainViewModel(db.dao) as T;
                }
            }
        }
    )
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen();

        setContent {
            QuickMathsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavbarView(mainViewModel, gameViewModel);
                }
            }
        }
    }
}
