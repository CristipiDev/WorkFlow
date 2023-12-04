package com.example.workflow.ui.workflow

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class WorkflowUiState (
    val complitedPercentage: Float = 0.0f,
    val iconHeight: Dp = 54.dp,
    val expandNewBox: Boolean = false,
    val expandedTaskBox: Boolean = false,
    val expandedStateBox: Boolean = false
)