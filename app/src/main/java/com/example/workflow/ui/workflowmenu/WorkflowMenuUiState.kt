package com.example.workflow.ui.workflowmenu

import androidx.compose.ui.Alignment
import com.example.workflow.domain.model.WorkflowModel

data class WorkflowMenuUiState (
    val alignmentSwitch: Alignment? = null,
    val showFirstColumn: Boolean = true,
    val showDialog: Boolean = false,
    val workflowData: WorkflowModel = WorkflowModel()
)