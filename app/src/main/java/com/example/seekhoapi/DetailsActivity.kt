package com.example.seekhoapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.seekhoapi.ui.theme.SeekhoApiTheme
import com.example.seekhoapi.ui.view.DetailScreen

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animeId = intent.getIntExtra("api_key", -1)
        enableEdgeToEdge()
        setContent {
            SeekhoApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = Color.LightGray) {
                    DetailScreen(animeId = animeId, paddingValues = it)
                }
            }
        }
    }
}

@Composable
fun Greeting1(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}