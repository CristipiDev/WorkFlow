package com.example.workflow.ui.workflowmenu

import androidx.compose.ui.Alignment

data class WorkflowMenuUiState (
    val alignmentSwitch: Alignment? = null,
    val showFirstColumn: Boolean = true,
    val showDialog: Boolean = false,
    val textFieldValue: String = ""
)