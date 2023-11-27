package com.example.workflow.domain.model

data class WorkflowModel (
    val workflowTitle: String = "",
    val stateList: ArrayList<StateModel> = ArrayList()
)