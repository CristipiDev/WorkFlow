package com.example.workflow.domain.model

data class WorkflowModel (
    val workflowId: Int = -1,
    val workflowTitle: String = "",
    val stateList: ArrayList<StateModel> = ArrayList()
)