package com.example.workflow.ui.workflow

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workflow.domain.model.TaskModel
import com.example.workflow.ui.workflow.tabsadd.AddTab

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

    val rotateIcon by animateFloatAsState(
        if (viewModel.uiState.showNewTaskBox) -45f else 0f,
        tween(durationMillis = 300), label = ""
    )

    val newTaksBoxOffset by animateDpAsState(
        if (viewModel.uiState.showNewTaskBox)
            viewModel.uiState.newTaskBoxHeight+20.dp+viewModel.uiState.titleNewStateHeight+40.dp
        else 0.dp,
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

                //Header title of workflow and percentage
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.verticalScroll(scrollState)) {
                    Text(text = viewModel.dataState.workflowInfo.workflowTitle,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f))
                    StatusCircularBarAndText(viewModel.uiState.complitedPercentage)

                }

                //Body
                LazyColumn() {
                    items(viewModel.dataState.workflowInfo.stateList) {state ->
                        StateColumn(state.stateTitle, state.taskList)
                    }
                }
            }
            val offsetBox = screenHeight-40.dp-viewModel.uiState.iconHeight-20.dp-newTaksBoxOffset
            //Box of the new task and state
            Column(modifier = Modifier.fillMaxWidth()
                .offset(y = offsetBox)) {
                AddTab(viewModel, density, rotateIcon)

            }

        }
    }

}

@Composable
private fun StatusCircularBarAndText(
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
private fun StateColumn(
    stateTitle: String,
    taskList: ArrayList<TaskModel>
) {
    Text(text = stateTitle, style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 20.dp))

    Column {
        taskList.forEach {task ->
            TaskCard(task)
        }
    }
}

@Composable
private fun TaskCard(
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