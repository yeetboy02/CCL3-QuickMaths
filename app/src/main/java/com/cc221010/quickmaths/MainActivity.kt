package com.cc221010.quickmaths

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cc221010.quickmaths.ui.mainViewModel
import com.cc221010.quickmaths.ui.theme.QuickMathsTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<mainViewModel>(
        factoryProducer = {
            object:ViewModelProvider.Factory{
                override fun <T:ViewModel> create(modelClass:Class<T>):T{
                    return mainViewModel() as T;
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickMathsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
