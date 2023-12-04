package com.example.workflow.ui.workflow.newtaskstate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.workflow.ui.workflow.WorkflowUiState
import com.example.workflow.ui.workflow.WorkflowViewModel

@Composable
fun NewMainBoxFragment(
    viewModel: WorkflowViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {

        AddButton(viewModel::setIconAddTaskHeight, viewModel::expandNewTaskBox,
            viewModel::expandNewBox, viewModel.uiState)

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)) {
            AnimatedVisibility(
                visible = viewModel.uiState.expandedTaskBox,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                NewTaskBoxFragment(viewModel::expandNewStateBox)
            }

            AnimatedVisibility(
                visible = viewModel.uiState.expandedStateBox,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                NewStateBoxFragment()
            }

        }
    }
}

@Composable
private fun AddButton(
    setIconAddTaskHeight: (Dp) -> Unit,
    expandNewTaskBox: () -> Unit,
    expandNewBox: () -> Unit,
    uiState: WorkflowUiState
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    val buttonWidth by animateDpAsState(
        targetValue = if (!uiState.expandedTaskBox && !uiState.expandedStateBox) 50.dp
        else screenWidth,
        tween(durationMillis = 300), label = ""
    )
    val rotateIcon by animateFloatAsState(
        if (uiState.expandNewBox) -45f else 0f,
        tween(durationMillis = 300), label = ""
    )

    //Add button
    Row(modifier = Modifier
        .size(buttonWidth+1.dp, 40.dp)
        .background(
            MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
        )
        .onGloballyPositioned {
            val iconHeight = with(density) {
                it.size.height.toDp()
            }
            setIconAddTaskHeight(iconHeight)
        }
        .padding(10.dp, 5.dp, 10.dp, 0.dp)) {
        if (uiState.expandedTaskBox || uiState.expandedStateBox) {
            Text(
                text = "New task",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .clickable { expandNewTaskBox() }
            )
        }
        Box(modifier = Modifier
            .clickable { expandNewBox() },
            contentAlignment = Alignment.Center) {
            Icon( imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .rotate(rotateIcon)
                    .size(40.dp))
        }
    }
}

