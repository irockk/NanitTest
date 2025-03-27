package com.example.nanit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.nanit.feature.details.presenation.DetailsRoute
import com.example.nanit.ui.theme.NanitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NanitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   DetailsRoute()
                }
            }
        }
    }
}