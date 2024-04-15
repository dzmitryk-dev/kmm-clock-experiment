package com.github.dzmitrykdev.clock

import Clock
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Clock()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    Clock()
}