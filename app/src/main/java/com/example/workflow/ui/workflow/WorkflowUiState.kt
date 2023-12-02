package com.example.workflow.ui.workflow

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WorkflowUiState (
    val complitedPercentage: Float = 0.0f,
    val iconHeight: Dp = 0.dp,
    val showNewTaskBox: Boolean = false,
    val showNewStateBox: Boolean = false,
    val newTaskBoxHeight: Dp = 0.dp,
    val newStateBoxHeight: Dp = 0.dp,
    val titleNewTaskHeight: Dp = 0.dp,
    val titleNewStateHeight: Dp = 0.dp
)