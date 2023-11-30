package com.example.workflow.ui.workflow

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workflow.domain.model.TaskModel

@Composable
fun WorkflowScreen(
    viewModel: WorkflowViewModel = hiltViewModel(),
    navController: NavController,
    workflowId: Int
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(
        key1 = scrollState.isScrollInProgress,
    ) {
        if (scrollState.isScrollInProgress) {
            navController.popBackStack()
        }
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val density = LocalDensity.current
    var iconHeight by remember { mutableStateOf(0.dp) }
    var newTaskBoxHeight by remember { mutableStateOf(0.dp) }

    var showNewTaskBox by remember { mutableStateOf(false) }
    val rotateIcon by animateFloatAsState(
        if (showNewTaskBox) -45f else 0f,
        tween(durationMillis = 300), label = ""
    )

    val newTaksBoxOffset by animateDpAsState(
        if (showNewTaskBox) newTaskBoxHeight+20.dp else 0.dp,
        tween(durationMillis = 300), label = ""
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .padding(top = 40.dp)) {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.verticalScroll(scrollState)) {
                    Text(text = viewModel.dataState.workflowInfo.workflowTitle,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f))
                    statusCircularBarAndText(viewModel.uiState.complitedPercentage)

                }
                LazyColumn() {
                    items(viewModel.dataState.workflowInfo.stateList) {state ->
                        stateColumn(state.stateTitle, state.taskList)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth()
                .offset(y = screenHeight-40.dp-iconHeight-20.dp-newTaksBoxOffset)) {
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .onGloballyPositioned {
                        iconHeight = with(density) {
                            it.size.height.toDp()
                        }
                    },
                    horizontalArrangement = Arrangement.End) {
                    Box(modifier = Modifier
                        .size(50.dp, 40.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        .clickable { showNewTaskBox = !showNewTaskBox }
                        .padding(top = 5.dp),
                        contentAlignment = Alignment.Center) {
                        Icon( imageVector = Icons.Filled.Add,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .rotate(rotateIcon)
                                .size(40.dp))
                    }
                }

                Column(modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(20.dp)
                    .onGloballyPositioned {
                        newTaskBoxHeight = with(density) {
                            it.size.height.toDp()
                        }
                    })
                {
                    Text(text = "New task",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.background)
                }
            }

        }
    }

}

@Composable
private fun statusCircularBarAndText(
    percentage: Float
){
    Box(contentAlignment = Alignment.Center) {
        Text(text = "${percentage*100}%", style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary)
        CircularProgressIndicator(progress = percentage,
            modifier = Modifier.size(50.dp),
            strokeWidth = 1.dp
        )
    }
}

@Composable
private fun stateColumn(
    stateTitle: String,
    taskList: ArrayList<TaskModel>
) {
    Text(text = stateTitle, style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 20.dp))

    Column {
        taskList.forEach {task ->
            taskCard(task)
        }
    }
}

@Composable
private fun taskCard(
    task: TaskModel
) {
    var textColor = MaterialTheme.colorScheme.background
    var backgroundColor = MaterialTheme.colorScheme.primary

    if (task.isCompleted) {
        textColor = MaterialTheme.colorScheme.primary
        backgroundColor = MaterialTheme.colorScheme.background
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(10.dp))

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = backgroundColor,
            shape = CircleShape
        )
        .border(
            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            shape = CircleShape
        )
        .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = task.taskTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor)
            Text(text = task.tastDescription, style = MaterialTheme.typography.bodySmall,
                color = textColor, maxLines = 1)
        }
        if (task.taskIsPriority)
            Text(text = "!",
                style = MaterialTheme.typography.labelMedium,
                color = textColor, modifier = Modifier.padding(horizontal = 5.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
}