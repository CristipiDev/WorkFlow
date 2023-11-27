package com.example.workflow.ui.workflowmenu

import com.example.workflow.domain.model.WorkflowModel

data class WorkflowMenuDataState (
    val workflowList: List<WorkflowModel> = emptyList()
)