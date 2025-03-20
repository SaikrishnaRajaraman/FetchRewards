package com.saikrishna.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.saikrishna.fetch.presentation.ui.ItemListScreen
import com.saikrishna.fetch.ui.theme.FetchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTheme {
                ItemListScreen()
            }
        }
    }
}