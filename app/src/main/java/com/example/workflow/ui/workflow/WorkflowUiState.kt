package com.example.workflow.ui.workflow

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WorkflowUiState (
    val complitedPercentage: Float = 0.0f,
    val iconHeight: Dp = 0.dp,
    val showNewTaskBox: Boolean = false,
    val newTaskBoxHeight: Dp = 0.dp
)