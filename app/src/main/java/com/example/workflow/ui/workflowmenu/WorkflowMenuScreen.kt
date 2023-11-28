package com.example.workflow.ui.workflowmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workflow.R
import com.example.workflow.ui.navigation.AppRoutes
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import com.example.workflow.ui.aboutapp.WorkflowAboutAppScreen

@Composable
fun WorkflowMenuScreen(
    viewModel: WorkflowMenuViewModel = hiltViewModel(),
    navController: NavController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val firstColumnOffset by animateDpAsState(
        if (viewModel.uiState.showFirstColumn) 0.dp else (-screenWidth),
        tween(durationMillis = 300), label = ""
    )

    val secondColumnOffset by animateDpAsState(
        if (viewModel.uiState.showFirstColumn) screenWidth else 0.dp,
        tween(durationMillis = 300), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = firstColumnOffset, y = 0.dp)
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(bottom = 20.dp)
        ) {

            headerRow(viewModel::setShowFirstColumn)

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .weight(1f)
            ) {
                items(viewModel.dataState.workflowList) { workflow ->
                    menuItem(workflow.workflowId, workflow.workflowTitle, navController)
                }
            }

            switchAndIconComponent(viewModel.uiState.alignmentSwitch,
                    darkTheme, onThemeUpdated, viewModel::onSwitchDarkTheme)
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .offset(x = secondColumnOffset, y = 0.dp)
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(20.dp, 0.dp, 20.dp, 20.dp)) {
            WorkflowAboutAppScreen(viewModel::setShowFirstColumn)
        }
    }




}

@Composable
private fun headerRow(
    onClickAbout: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)) {
            Text(text = "ICONO")
            /*TODO poner el icono de la app*/
        }
        Text(text = "i",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(50.dp)
                .clickable { onClickAbout() },
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center)
    }
}


@Composable
private fun menuItem(
    id: Int,
    text: String,
    navController: NavController
){
    Text(text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(AppRoutes.WorkflowInfo.route + "/$id") })
}

@Composable
private fun switchAndIconComponent(
    alignmentSwitch: Alignment?,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onSwitchDarkTheme: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.lightbulb_fill),
            contentDescription = "Light mode",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(35.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
        )
        alignmentSwitch?.let {
            switchComponent(
                darkTheme, onThemeUpdated, onSwitchDarkTheme,
                it
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(35.dp)
        )
    }
}

@Composable
fun switchComponent(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    changeSwitch: (Boolean) -> Unit,
    alignment: Alignment
) {
    Box(modifier = Modifier
        .size(width = 60.dp, height = 30.dp)
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = CircleShape
        )
        .clickable {
            onThemeUpdated()
            changeSwitch(darkTheme)
        },
        contentAlignment = alignment){
        Box(modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.background),
                shape = CircleShape
            ))
    }
}

@Preview
@Composable
fun Preview() {
}