package com.example.workflow.ui.aboutapp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WorkflowAboutAppScreen(
    navController: NavController
) {
    /*Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "About app")

        Box(modifier = Modifier.size(100.dp)
            .background(Color.Magenta)
            .clickable {
                navController.popBackStack()
                })
    }*/

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var showFirstColumn by remember { mutableStateOf(true) }

    val firstColumnOffset by animateDpAsState(
        if (showFirstColumn) 0.dp else (-screenWidth),
        tween(durationMillis = 500), label = ""
    )

    val secondColumnOffset by animateDpAsState(
        if (showFirstColumn) screenWidth else 0.dp,
        tween(durationMillis = 500), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = firstColumnOffset, y = 0.dp)
                .background(Color.Green)
        ) {
            Text("First Column Content")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = secondColumnOffset, y = 0.dp)
                .background(Color.Blue)
        ) {
            Text("Second Column Content")
        }

        Button(
            onClick = { showFirstColumn = !showFirstColumn },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(if (showFirstColumn) "Show Second Column" else "Show First Column")
        }
    }
}