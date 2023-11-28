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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun WorkflowMenuScreen(
    viewModel: WorkflowMenuViewModel = hiltViewModel(),
    navController: NavController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(bottom = 20.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { navController.navigate(AppRoutes.WorkflowAboutApp.route) }) {
            Text(text = "ICONO")
            /*TODO poner el icono de la app*/
        }

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .weight(1f)
        ) {
            items(viewModel.dataState.workflowList) { workflow ->
                menuItem(workflow.workflowId, workflow.workflowTitle, navController)
            }
        }

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
            viewModel.uiState.alignmentSwitch?.let {
                switchComponent(
                    darkTheme, onThemeUpdated, viewModel::onSwitchDarkTheme,
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
            .clickable { navController.navigate(AppRoutes.WorkflowInfo.route + "/$id") })
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