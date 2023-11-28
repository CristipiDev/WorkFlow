package com.example.workflow.ui.aboutapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AboutAppScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "About app")

        Box(modifier = Modifier.size(100.dp)
            .background(Color.Magenta)
            .clickable {
                navController.popBackStack()
                })
    }
}